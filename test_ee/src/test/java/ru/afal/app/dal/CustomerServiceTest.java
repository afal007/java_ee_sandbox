package ru.afal.app.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ru.afal.app.business.CustomerService;
import ru.afal.app.model.Customer;

public class CustomerServiceTest {

	private static EntityManagerFactory emf;
	private static CustomerService customerService;

	@BeforeClass
	public static void setup() {
		emf = Persistence.createEntityManagerFactory( "PU_test" );
		customerService = new CustomerService();
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

		Customer customer = customerService.createCustomer( name, login );

		Assert.assertEquals( "Имя", 	name, 	customer.getName() );
		Assert.assertEquals( "Логин", 	login, 	customer.getLogin() );

		Assert.assertNotNull( "Пароль", customer.getPassword() );

		System.out.print( customer.getPassword() );
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