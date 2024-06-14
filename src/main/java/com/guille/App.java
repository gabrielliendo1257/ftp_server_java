package com.guille;

import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        try {
            // Crear un UserManager basado en el archivo users.properties
            PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
            userManagerFactory.setFile(new File("users.properties"));

            UserManager userManager = userManagerFactory.createUserManager();

            // Crear y guardar un nuevo usuario
            BaseUser newUser = new BaseUser();
            newUser.setName("piter");
            newUser.setPassword("pass");
            newUser.setHomeDirectory("D:/ciber");
            userManager.save(newUser);

            // Obtener todos los nombres de usuario
            String[] usernames = userManager.getAllUserNames();
            for (String username : usernames) {
                User user = userManager.getUserByName(username);
                System.out.println("Usuario: " + user.getName());
                System.out.println("Directorio de inicio: " + user.getHomeDirectory());
                System.out.println("Permisos: " + user.getAuthorities());
            }

            // Configurar y arrancar el servidor FTP
            FtpServerFactory serverFactory = new FtpServerFactory();
            ListenerFactory factory = new ListenerFactory();
            factory.setPort(21); // Puerto del servidor FTP

            serverFactory.addListener("default", factory.createListener());
            serverFactory.setUserManager(userManager);

            FtpServer server = serverFactory.createServer();
            server.start();
            System.out.println("FTP Server started on port 21...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
