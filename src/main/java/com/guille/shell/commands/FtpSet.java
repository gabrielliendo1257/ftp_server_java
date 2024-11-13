package com.guille.shell.commands;

import com.guille.mapper.CustomerMapper;
import com.guille.models.persist.Customer;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "ftpset", description = "Setear los properties del ftp.")
public class FtpSet implements Callable<Integer> {

  private CustomerMapper customerMapper = new CustomerMapper();

  // @Autowired private CustomerService customerService;

  @Option(names = {"-u", "--username"}, description = "Setter username.")
  private String username;

  @Option(names = {"-p", "--password"}, description = "Setter password.")
  private String password;

  @Override
  public Integer call() throws Exception {
    System.out.println("Username: " + this.username);
    System.out.println("Password: " + this.password);
    var customer = new Customer(username, password, "D:/");

    this.customerMapper.createTablaCustomer();
    System.out.println(customer);
    this.customerMapper.saveCustomer(customer);

    System.out.println("Customer persistido con exito.");

    System.out.println("Customer recuperado con id=2: " +
                       this.customerMapper.findCustomerById(2));

    this.customerMapper.recoveryData();

    return 0;
  }
}
