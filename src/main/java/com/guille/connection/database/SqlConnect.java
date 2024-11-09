package com.guille.connection.database;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlConnect {

  private static String url;

  private Connection conn = null;

  private String statementSql = "INSERT INTO ";

  static {
    url = "jdbc:sqlite:" + Paths.get("").toAbsolutePath().toString() +
          "/db/imp_data.db";
    log.info("Ruta raíz del proyecto en bloque estático: " + url);
  }

  public Connection initConnection() {
    try {

      if (conn == null) {
        conn = DriverManager.getConnection(url);
      }
      log.info("Conexion exitosa con la base de datos.");
      log.info("Path de la base de datos: " + url);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
}
