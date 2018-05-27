package ru.afal.app.business.ejb;

import java.math.BigDecimal;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ru.afal.app.model.Customer;

public class CustomerEJBTest {

    static EJBContainer ejbContainer;
    static Context context;

    @BeforeClass
    public static void beforeClass() {
        ejbContainer = EJBContainer.createEJBContainer( );
        context = ejbContainer.getContext();
    }

    @AfterClass
    public static void afterClass() {
        if( ejbContainer != null ) {
            ejbContainer.close();
    }   }



    @Test
    public void findCustomers() {
    }

    @Test
    public void insertCustomer() {
        Customer customer = new Customer( "Name", "Login", "password", new BigDecimal( 10.0 ) );
        try {
            CustomerEJB customerEJB = (CustomerEJB) context.lookup( "java:global/classes/CustomerEJB" );
            customerEJB.insertCustomer( customer );
        } catch ( NamingException e ) {
            e.printStackTrace();
        }
    }
}