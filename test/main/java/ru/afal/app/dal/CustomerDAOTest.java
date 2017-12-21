package ru.afal.app.dal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.afal.app.model.Customer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class CustomerDAOTest {

	private static EntityManagerFactory emf;

	@Before
	public void setup() {
		emf = Persistence.createEntityManagerFactory( "PU_test" );
	}

	@After
	public void tearDown() {
		if( emf.isOpen() ) {
			emf.close();
		}
	}

	@Test
	public void create() {
		String name = "Name";
		String login = "login";
		String password = "password";
		BigDecimal balance = new BigDecimal( 1000 );

		Customer customer = new Customer( name, login, password, balance );

		CustomerDAO dao = new CustomerDAO( emf );
		assertTrue( dao.create( customer ) );
		// TODO: достать из базы, проверить
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