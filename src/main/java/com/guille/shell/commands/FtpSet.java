package com.guille.shell.commands;

import com.guille.models.persist.Customer;
import com.guille.service.CustomerService;
import com.guille.utils.Networking;
import java.util.concurrent.Callable;
import lombok.ToString;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "ftp", description = "Setear los properties del ftp.")
public class FtpSet implements Callable<Integer> {

  private CustomerService customerService = new CustomerService();
  private Networking net = new Networking();

  // @Autowired private CustomerService customerService;

  @ArgGroup(exclusive = true, validate = false)
  SetterProperties setterProperties;

  @ArgGroup(exclusive = true, validate = false) RunnerFtp runnerFtp;

  @ToString
  static class SetterProperties {

    @Option(names = {"-u", "--username"}, description = "Setter username.")
    String username;

    @Option(names = {"-p", "--password"}, description = "Setter password.")
    String password;

    @Option(names = {"-d", "--directory"},
            description = "Setter Home irectory.")
    String homeDir;
  }

  @ToString
  static class RunnerFtp {

    @Option(names = {"-a", "--account"},
            description = "Usuario ya authenticado para usarlo con el ftp",
            required = true, type = String.class)
    String username;

    @Option(names = {"-h", "--host"}, description = "Host de la maquina.",
            required = false, type = String.class)
    String host;

    @Option(names = {"-x", "--port"}, description = "Puerto de escucha.",
            required = true, type = Integer.class)
    Integer port;
  }

  @Override
  public Integer call() throws Exception {

    if (this.setterProperties != null) {

      var customer = new Customer(this.setterProperties.username,
                                  this.setterProperties.password,
                                  this.setterProperties.homeDir);

      this.customerService.createTableCustomer();
      this.customerService.saveCustomer(customer);

    } else if (this.runnerFtp != null) {

      this.customerService.printDataRecovery();
      this.customerService.ftpRun(this.runnerFtp.port,
                                  this.net.getInetAddress());
    }

    return 0;
  }
}
