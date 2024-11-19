package com.guille.service;

import com.guille.manager.CustomUserManager;
import com.guille.mapper.CustomerMapper;
import com.guille.models.persist.Customer;
import java.util.List;
import java.util.Optional;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;

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

  public void ftpRun(Integer port, String host) {
    CustomUserManager cManager = new CustomUserManager();
    cManager.save(this.customerMapper.findAllCustomers());

    FtpServerFactory serverFactory = new FtpServerFactory();
    ListenerFactory listenerFactory = new ListenerFactory();
    listenerFactory.setPort(port); // Puerto del servidor FTP
    listenerFactory.setServerAddress(host);

    serverFactory.addListener("default", listenerFactory.createListener());
    serverFactory.setUserManager(cManager.userManager);
    FtpServer server = serverFactory.createServer();
    try {
      server.start();
    } catch (FtpException e) {
      e.printStackTrace();
    }

    System.out.println("FTP Server started on " + host + ":" + port + "...");
  }

  public void printDataRecovery() { this.customerMapper.recoveryData(); }
}
