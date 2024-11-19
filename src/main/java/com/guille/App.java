package com.guille;

import com.guille.shell.commands.FtpSet;
import picocli.CommandLine;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {

    new CommandLine(new FtpSet()).execute(args);
  }
}
