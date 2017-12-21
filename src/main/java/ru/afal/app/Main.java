package ru.afal.app;

import ru.afal.app.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
	public static void main (String[] args) {
		Customer customer = new Customer( "Name", "login", "password", new BigDecimal( 10 ) );

		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "PU" );
		EntityManager em = emf.createEntityManager();

		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist( customer );
			et.commit();
		} catch ( Exception e ) {
			System.out.print( "Everything is wrong" );
		} finally {
			emf.close();
			em.close();
		}
	}
}
