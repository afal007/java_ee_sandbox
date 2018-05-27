package ru.afal.app.business.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ru.afal.app.model.Customer;

@Stateless
public class CustomerEJB implements CustomerInterface{

    @PersistenceContext( unitName = "PU-JTA" )
    EntityManager entityManager;

    @Override
    public List<Customer> findCustomers() {
        TypedQuery<Customer> namedQuery = entityManager.createNamedQuery( Customer.FIND_ALL, Customer.class );

        return namedQuery.getResultList();
    }

    @Override
    public Customer insertCustomer( Customer customer ) {
        entityManager.persist( customer );
        return customer;
    }

}
