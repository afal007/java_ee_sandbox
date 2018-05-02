package ru.afal.app.dal;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ru.afal.app.model.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
@Ignore
public class CustomerDAOTest {

	private static EntityManagerFactory emf;

	@BeforeClass
	public static void setup() {
		emf = Persistence.createEntityManagerFactory( "PU_test" );
	}

	@Before
	public void prepDB() {
		EntityManager entityManager = emf.createEntityManager();
		Query deleteAll = entityManager.createNativeQuery( "DELETE FROM CUSTOMER; DELETE FROM USER; DELETE FROM CUSTOMER_USER; DELETE FROM TESTEMBEDDABLEID" );
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		deleteAll.executeUpdate();
		transaction.commit();
	}

	@AfterClass
	public static void tearDown() {
		if( emf.isOpen() ) {
			emf.close();
		}
	}

	@Test
	public void create() {
		String      name     = "Name";
		String      login    = "login";
		String      password = "password";
		BigDecimal  balance  = new BigDecimal( 1000.1 );

		Customer customer = new Customer( name, login, password, balance );

		CustomerDAO dao = new CustomerDAO( emf );
		assertTrue( dao.create( customer ) );

		TypedQuery<Customer> query = emf.createEntityManager().createQuery( "SELECT c FROM Customer c", Customer.class );
		List<Customer> resultList = query.getResultList();

		assertTrue( "Нашли ровно 1 сущность", resultList.size() == 1 );
		assertEquals( customer, resultList.get( 0 ) );
	}

	@Test
	public void find() {
	}

	@Test
	public void update() {
	}

	@Test
	public void delete() {
	}
}