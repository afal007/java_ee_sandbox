import ru.afal.app.model.Customer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CustomerService {

	@Inject
	private EntityManager entityManager;

	public Customer createCustomer( String name, String login, String password ) {
		Customer newCustomer = new Customer( name, login, password, new BigDecimal( "0" ) );

		return newCustomer;
	}
}
