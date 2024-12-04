package com.guille.shell.commands;

import com.guille.connection.ftp.FileTServer;
import com.guille.service.CustomerService;
import com.guille.utils.Networking;
import java.util.concurrent.Callable;
import lombok.ToString;
import org.apache.ftpserver.ftplet.FtpException;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "ftp", description = "Setear los properties del ftp.",
         subcommands = {FtpConnect.class, FtpSave.class},
         mixinStandardHelpOptions = true)
public class FtpSet implements Callable<Integer> {

  private CustomerService customerService = new CustomerService();
  private final Networking net = new Networking();
  private final FileTServer fileTServer = new FileTServer();

  @ArgGroup(exclusive = true, validate = false) private RunnerFtp runnerFtp;

  @ToString
  static class RunnerFtp {

    @Option(names = {"--public"},
            description = "Activa el modo de host publico.", required = false)
    Boolean publicHost;

    // @Parameters(index = "0", description = "Host de la maquina.", echo =
    // true, type = InetAddress.class) InetAddress host;

    @Option(names = {"-x", "--port"}, description = "Puerto de escucha.",
            required = true, type = Integer.class)
    Integer port;
  }

  @Override
  public Integer call() throws Exception {

    FileTServer fileTServer =
        new FileTServer(this.runnerFtp.port, this.runnerFtp.publicHost);
    try {
      fileTServer.init();
    } catch (FtpException e) {
      System.out.println("Problemas al iniciar el servidor --> [ERROR] " +
                         e.getMessage());
      return 2;
    }

    return 0;
  }
}
