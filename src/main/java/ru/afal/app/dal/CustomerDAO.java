package ru.afal.app.dal;

import ru.afal.app.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CustomerDAO {

	private EntityManagerFactory emf;

	public CustomerDAO( EntityManagerFactory emf ) {
		this.emf = emf;
	}

	public boolean create ( Customer customer ) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();

			transaction.begin();
			em.persist( customer );
			transaction.commit();
		} catch ( Exception ex ) {
			if( transaction != null ) {
				transaction.rollback();
			}
			ex.printStackTrace();

			return false;
		} finally {
			if( em.isOpen() ) {
				em.close();
			}
		}

		return true;
	}

	public Customer find ( Long id ) {
		EntityManager em = emf.createEntityManager();

		Customer res = null;
		try {
			res = em.find( Customer.class, id );
		} catch ( Exception ex ) {
			ex.printStackTrace();
		} finally {
			if( em.isOpen() ) {
				em.close();
			}
		}

		return res;
	}

	public boolean update ( Customer customer ) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction transaction = null;
		try {
			Customer customerDb = em.find( Customer.class, customer.getId() );

			transaction = em.getTransaction();
			transaction.begin();

			customerDb.setName      ( customer.getName() );
			customerDb.setId        ( customer.getId() );
			customerDb.setBalance   ( customer.getBalance() );

			transaction.commit();
		} catch ( Exception ex ) {
			if( transaction != null ) {
				transaction.rollback();
			}
			ex.printStackTrace();

			return false;
		} finally {
			if( em.isOpen() ) {
				em.close();
			}
		}

		return true;
	}

	public boolean delete ( Long id ) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();

			Customer customerDb = em.find( Customer.class, id );

			transaction.begin();
			em.remove( customerDb );
			transaction.commit();
		} catch ( Exception ex ) {
			if( transaction != null ) {
				transaction.rollback();
			}
			ex.printStackTrace();

			return false;
		} finally {
			if( em.isOpen() ) {
				em.close();
			}
		}

		return true;
	}
}
