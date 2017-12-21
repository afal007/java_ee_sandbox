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
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			em.persist( customer );
			et.commit();

			Customer customerDb = em.find( Customer.class, 1L );
			System.out.println( customerDb );
		} catch ( Exception e ) {
			if( et != null ) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			if( emf.isOpen() ) {
				emf.close();
			}
			if ( em.isOpen() ) {
				em.close();
			}
		}
	}
}
