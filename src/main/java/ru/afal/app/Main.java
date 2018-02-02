package ru.afal.app;

import ru.afal.app.model.Customer;
import ru.afal.app.model.User;
import ru.afal.app.model.enums.UserType;
import ru.afal.app.utils.TransactionHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Main {
	private static EntityManagerFactory emf     = Persistence.createEntityManagerFactory( "PU" );
	private static EntityManager        em      = emf.createEntityManager();

	public static void main (String[] args) {
		insertData();

		execJPQL();

		execNativeSQL();

		execNamedQuery();

		execCriteriaQuery();
	}

	private static void execNamedQuery() {
		TypedQuery<Customer> query = em.createNamedQuery( Customer.FIND_ALL, Customer.class );
		List<Customer> resultList = query.getResultList();
		System.out.println( "NamedQuery:\t" + resultList );
	}

	private static void execNativeSQL() {
		TransactionHelper helper = new TransactionHelper( Const.PERSISTENCE_UNIT );
		List<Object[]> resultList = helper.nativeQuery( "SELECT * FROM CUSTOMER" );

		Object[] list = resultList.get( 0 );
		System.out.println( "NativeQuery:\t" + list[0] + " "  + list[1] + " " + list[2] + " "+ list[3] + " "+ list[4] );
	}

	private static void execCriteriaQuery() {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		CriteriaQuery<Customer> query = builder.createQuery( Customer.class );
		Root<Customer> root = query.from( Customer.class );

		query.select( root ).where( builder.equal( root.get( "name" ), "Name" ) );

		List<Customer> resultList = em.createQuery( query ).getResultList();

		System.out.println( "CriteriaQuery:\t" + resultList );
	}

	private static void execJPQL() {
		Query query = em.createQuery( "SELECT c FROM Customer c WHERE c.name = 'Name'" );
		List resultList = query.getResultList();

		System.out.println( "JPQL:\t" + resultList );
	}

	private static void insertData() {
		TransactionHelper helper = new TransactionHelper( Const.PERSISTENCE_UNIT );

		helper.transaction( (em) -> {
			Customer customer = new Customer( "Name", "login", "password", new BigDecimal( 10 ) );
			User user = new User( "Name", "login", "password", UserType.USER );

			customer.setUserList( Collections.singletonList( user ) );
			em.persist( customer );
		} );

		helper.transaction( (em) -> {
			Customer customerDb = em.find( Customer.class, 1L );
			System.out.println( "EM:\t" + customerDb );
			System.out.println( customerDb.getUserList() );
		});
}   }