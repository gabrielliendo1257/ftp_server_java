package com.guille.service;

import com.guille.models.persist.Customer;
import com.guille.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired private CustomerRepository customerRepository;

  public void saveCustomer(Customer customer) {
    this.customerRepository.save(customer);
  }

  public void deleteCustomer(Customer customer) {
    this.customerRepository.delete(customer);
  }

  public void deleteCustomerById(Integer id) {
    this.customerRepository.deleteById(id);
  }

  public Customer getCustomerById(Integer id) {
    return this.customerRepository.getReferenceById(id);
  }
}
