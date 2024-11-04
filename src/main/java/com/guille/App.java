package com.guille;

import com.guille.connection.database.SqlConnect;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {

    SqlConnect sqlConnect = new SqlConnect();
    sqlConnect.init();

    // Obtén la ruta del directorio actual
    // Path currentPath = Paths.get("").toAbsolutePath();
    // // Construye la ruta hacia el pom.xml
    // Path pomPath = currentPath.resolve("pom.xml");
    //
    // if (pomPath.toFile().exists()) {
    // System.out.println("La ruta raíz del proyecto es: " +
    // currentPath.toString());
    // } else {
    // System.out.println("No se encontró pom.xml en la ruta: " +
    // currentPath.toString());
    // }

    // try {
    // InMemoryUserManager inMemoryUserManager = new InMemoryUserManager();
    // inMemoryUserManager.save(
    // List.of(new Customer("pedro", "12345", "E:/",
    // List.of(new Authorities(Permissions.WRITE))),
    // new Customer("miguel", "toast", "E:/",
    // List.of(new Authorities(Permissions.WRITE))),
    // new Customer("jose", "cast", "D:/cursos/django",
    // List.of(new Authorities(Permissions.WRITE)))));
    //
    // // Configurar y arrancar el servidor FTP
    // FtpServerFactory serverFactory = new FtpServerFactory();
    // ListenerFactory listenerFactory = new ListenerFactory();
    // listenerFactory.setPort(21); // Puerto del servidor FTP
    // listenerFactory.setServerAddress("192.168.63.18");
    //
    // serverFactory.addListener("default", listenerFactory.createListener());
    // serverFactory.setUserManager(inMemoryUserManager.userManager);
    // FtpServer server = serverFactory.createServer();
    // server.start();
    //
    // System.out.println("FTP Server started on port 21...");
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
  }
}
