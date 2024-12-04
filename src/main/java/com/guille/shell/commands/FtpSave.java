package com.guille.shell.commands;

import com.guille.models.persist.Customer;
import com.guille.service.CustomerService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "save", description = "Guardar un usuario.",
         mixinStandardHelpOptions = true)
public class FtpSave implements Callable<Integer> {

  private CustomerService customerService = new CustomerService();

  @Option(names = {"-u", "--username"}, description = "Setter username.",
          required = true)
  String username;

  @Option(names = {"-p", "--password"}, description = "Setter password.",
          interactive = true)
  String password;

  @Option(names = {"-d", "--directory"}, description = "Setter Home directory.",
          type = Path.class)
  Path homeDir;

  @Override
  public Integer call() throws Exception {
    Customer customer =
        new Customer(this.username, this.password, this.homeDir.toString());

    if (!Files.exists(this.homeDir) && !Files.isDirectory(this.homeDir)) {
      System.out.println("El directorio no existe.");
      return 1;
    }

    this.customerService.createTableCustomer();
    this.customerService.saveCustomer(customer);
    return 0;
  }
}
