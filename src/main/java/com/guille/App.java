package com.guille;

import java.util.List;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import com.guille.manager.InMemoryUserManager;
import com.guille.models.Entity;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        try {
            InMemoryUserManager inMemoryUserManager = new InMemoryUserManager();
            inMemoryUserManager.save(List.of(
                    new Entity("pedro", "12345", List.of(new WritePermission()), "D:/"),
                    new Entity("miguel", "toast", List.of(new WritePermission()), "E:/"),
                    new Entity("jose", "cast", List.of(new WritePermission()), "D:/cursos/django")));

            // Configurar y arrancar el servidor FTP
            FtpServerFactory serverFactory = new FtpServerFactory();
            ListenerFactory factory = new ListenerFactory();
            factory.setPort(21); // Puerto del servidor FTP
            factory.setServerAddress("192.168.63.18");

            serverFactory.addListener("default", factory.createListener());
            serverFactory.setUserManager(inMemoryUserManager.userManager);

            FtpServer server = serverFactory.createServer();
            server.start();
            System.out.println("FTP Server started on port 21...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
