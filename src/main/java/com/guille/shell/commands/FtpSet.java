package com.guille.shell.commands;

import com.guille.enums.Permissions;
import com.guille.models.persist.Authorities;
import com.guille.models.persist.Customer;
import com.guille.service.CustomerService;
import java.util.Arrays;
import java.util.concurrent.Callable;
import org.springframework.beans.factory.annotation.Autowired;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "ftpset", description = "Setear los properties del ftp.")
public class FtpSet implements Callable<Integer> {

  @Autowired private CustomerService customerService;

  @Option(names = {"-u", "--username"}, description = "Setter username.")
  private String username;

  @Option(names = {"-p", "--password"}, description = "Setter password.")
  private String password;

  @Override
  public Integer call() throws Exception {
    System.out.println("Username: " + this.username);
    System.out.println("Password: " + this.password);
    var customer =
        new Customer(username, password, "D:/",
                     Arrays.asList(new Authorities(Permissions.WRITE)));
    this.customerService.saveCustomer(customer);
    System.out.println("Customer guardado con ezito!");
    var cust = this.customerService.getCustomerById(1);
    System.out.println(
        this.customerService.getCustomerById(cust.getId()).toString());
    return 0;
  }
}
