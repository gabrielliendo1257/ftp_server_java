package com.guille.service;

import com.guille.mapper.CustomerMapper;
import com.guille.models.persist.Customer;
import java.util.List;
import java.util.Optional;

public class CustomerService {

  private CustomerMapper customerMapper = new CustomerMapper();

  public void saveCustomer(Customer customer) {
    this.customerMapper.saveCustomer(customer);
  }

  public Optional<Customer> findCustomerByUsername(String username) {
    return this.customerMapper.findCustomerByUsername(username);
  }

  public void createTableCustomer() {
    this.customerMapper.createTablaCustomer();
  }

  public List<Customer> findAllCustomers() {
    return this.customerMapper.findAllCustomers();
  }

  public void printDataRecovery() { this.customerMapper.recoveryData(); }
}
