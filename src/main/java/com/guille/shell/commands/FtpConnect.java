package com.guille.shell.commands;

import java.util.concurrent.Callable;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "connect",
         description = "Commando para conectar al servidor del ftp.")
public class FtpConnect implements Callable<Integer> {

  @Option(names = {"-u", "--username"}, description = "Setter username.",
          required = true)
  String username;

  @Option(names = {"-p", "--password"}, description = "Setter password.")
  String password;

  @Parameters(index = "0", description = "Host del servidor.",
              type = String.class)
  String hostServer;

  @Option(names = {"-x", "--port"}, description = "Puerto del servidor ftp.",
          required = false, type = Integer.class)
  Integer port;

  @Option(names = {"-f", "--file"},
          description = "Nombre del fichero a descargar.", required = false)
  String file;

  @Override
  public Integer call() throws Exception {
    FTPClient ftpClient = new FTPClient();

    ftpClient.connect(this.hostServer, this.port);

    Boolean login = ftpClient.login(this.username, this.password);

    if (!login) {
      System.out.println("Usuario y/o password incorrecto.");
      return 1;
    }
    System.out.println("Usuario authenticado con exito.");

    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    ftpClient.enterLocalActiveMode();

    System.out.println("Archivos del directorio ftp.");
    String[] files = ftpClient.listNames();
    for (String file : files) {
      System.out.println(" - " + file);
    }

    return 0;
  }
}
