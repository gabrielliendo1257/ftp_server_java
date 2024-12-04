package com.guille.shell.commands;

import com.guille.connection.ftp.FileTServer;
import java.util.concurrent.Callable;
import org.apache.ftpserver.ftplet.FtpException;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "server", description = "Comando para el servidor.")
public class FtpServer implements Callable<Integer> {

  @Option(names = {"--public"}, description = "Activa el modo de host publico.",
          required = false)
  Boolean publicHost;

  // @Parameters(index = "0", description = "Host de la maquina.", echo = true,
  // type = InetAddress.class)
  // InetAddress host;

  @Option(names = {"-x", "--port"}, description = "Puerto de escucha.",
          required = true, type = Integer.class)
  Integer port;

  @Override
  public Integer call() throws Exception {
    FileTServer fileTServer = new FileTServer(this.port, this.publicHost);
    System.out.println("Public --> " + this.publicHost.toString());
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
