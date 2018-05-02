package ru.afal.app.business;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ru.afal.app.business.interceptors.interceptorbinding.Loggable;
import ru.afal.app.business.passwordgen.GeneratorType;
import ru.afal.app.business.passwordgen.PasswordGenType;
import ru.afal.app.business.passwordgen.PasswordGenerator;
import ru.afal.app.model.Customer;

@Loggable
public class CustomerService {

    @Inject
    private EntityManager em;

    @Inject
    @PasswordGenType( GeneratorType.ALPHANUMERIC )
    private PasswordGenerator passGen;


    private final static int DEFAULT_PASS_LENGTH = 10;

    public Customer createCustomer( String name, String login ) {
        Customer customer = new Customer( name, login, passGen.generate( DEFAULT_PASS_LENGTH ), new BigDecimal( "0" ) );

        em.persist( customer );

        return customer;
    }
}
