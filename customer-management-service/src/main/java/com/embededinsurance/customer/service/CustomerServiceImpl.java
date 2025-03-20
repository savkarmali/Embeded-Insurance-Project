package com.embededinsurance.customer.service;

import com.embededinsurance.customer.dto.Customer;
import com.embededinsurance.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updateCustomer) {
        Customer customer = getCustomer(id);
        customer.setName(updateCustomer.getName());
        customer.setEmail(updateCustomer.getEmail());
        customer.setMobile(updateCustomer.getMobile());
        customer.setAddress(updateCustomer.getAddress());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
