package com.guille.connection.ftp;

import com.guille.manager.CustomUserManager;
import com.guille.mapper.CustomerMapper;
import com.guille.utils.Networking;
import lombok.Setter;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;

public class FileTServer {

  private final CustomerMapper customerMapper = new CustomerMapper();

  private final Networking net = new Networking();

  @Setter private Integer port;

  @Setter private String host;

  public FileTServer() {}

  public FileTServer(Integer port, Boolean isPublic) {
    this.port = port;
    if (isPublic != null) {
      this.host = this.net.getIpLAN();
    } else {
      this.host = this.net.getInetAddress();
    }
  }

  public void init() throws FtpException {
    CustomUserManager cManager = new CustomUserManager();
    cManager.save(this.customerMapper.findAllCustomers());

    FtpServerFactory serverFactory = new FtpServerFactory();
    ListenerFactory listenerFactory = new ListenerFactory();
    listenerFactory.setPort(this.port); // Puerto del servidor FTP
    listenerFactory.setServerAddress(this.host);

    serverFactory.addListener("default", listenerFactory.createListener());
    serverFactory.setUserManager(cManager.userManager);
    FtpServer server = serverFactory.createServer();
    server.start();

    System.out.println("FTP Server started on " + host + ":" + port + "...");
  }
}
