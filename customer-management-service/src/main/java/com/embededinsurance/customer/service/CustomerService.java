package com.embededinsurance.customer.service;

import com.embededinsurance.customer.dto.Customer;

import java.util.List;

public interface CustomerService {

    Customer getCustomer(Long id);

    List<Customer> getAllCustomers();

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);
}
