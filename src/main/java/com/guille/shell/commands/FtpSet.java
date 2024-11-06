package com.guille.shell.commands;

import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Command(name = "ftpset", description = "Setear los properties del ftp.")
public class FtpSet implements Callable<Integer> {

  @Option(names = {"-u", "--username"}, description = "Setter username.")
  private String username;

  @Option(names = {"-p", "--password"}, description = "Setter password.")
  private String password;

  @Override
  public Integer call() throws Exception {
    log.info("Username: " + this.username);
    log.info("Password: " + this.password);
    return 0;
  }
}
