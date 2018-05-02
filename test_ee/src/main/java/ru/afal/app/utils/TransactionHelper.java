package ru.afal.app.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionHelper {

	private final EntityManagerFactory factory;

	private final static Map<String, EntityManagerFactory> FACTORY_CACHE = new HashMap<>();

	public TransactionHelper( String persistenceUnit ) {
		EntityManagerFactory entityManagerFactory = FACTORY_CACHE.get( persistenceUnit );
		if( entityManagerFactory == null ) {
			this.factory = Persistence.createEntityManagerFactory( persistenceUnit );
			FACTORY_CACHE.put( persistenceUnit, this.factory );
		} else {
			this.factory = entityManagerFactory;
		}
	}

	/**
	 * Метод предназначен для выполнения транзакции в заданном Persistence Unit.
	 *
	 * @param action    Последовательности операций в транзакции.
	 *                  Параметр обязательный.
	 *
	 * @throws IllegalArgumentException если на вход передан <code>null</code>
	 */
	public void transaction( TransactionAction action ) {
		if( action == null ) {
			throw new IllegalArgumentException( "action не может быть null" );
		}

		EntityManager           entityManager   = factory.createEntityManager();
		EntityTransaction       transaction     = entityManager.getTransaction();

		try {
			transaction.begin();
			action.perform( entityManager );
			transaction.commit();
		} catch ( Exception e ) {
			transaction.rollback();
			throw new RuntimeException( "Ошибка во время выполнения транзакции: ", e );
		} finally {
			if( entityManager.isOpen() ) {
				entityManager.close();
	}   }   }

	@SuppressWarnings( "unchecked" )
	public List<Object[]> nativeQuery( String query ) {
		EntityManager entityManager = factory.createEntityManager();
		Query nativeQuery = entityManager.createNativeQuery( query );

		return (List<Object[]>) nativeQuery.getResultList();
	}

	/**
	 * Функциональный интерфейс для определения последовательности операций в транзакции.
	 * (Можно было использовать {@link java.util.function.Consumer<EntityManager>})
	 */
	public interface TransactionAction {
		/**
		 * Метод предназначен для определения последовательности операций в транзакции.
		 *
		 * @param entityManager     {@link EntityManager} транзакции.
		 *                           Параметр обязательный.
		 */
		void perform( EntityManager entityManager );
	}
}
