package ru.afal.app.business.ejb;

import java.util.List;

import javax.ejb.Local;

import ru.afal.app.model.Customer;

@Local
public interface CustomerInterface {
    List<Customer> findCustomers();
    Customer insertCustomer( Customer customer );
}
