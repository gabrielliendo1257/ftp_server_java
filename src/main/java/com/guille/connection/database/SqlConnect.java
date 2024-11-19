package com.guille.connection.database;

import com.guille.configuration.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class SqlConnect {

  private static Connection conn = null;

  private static final DatabaseConfiguration databaseConfiguration =
      new DatabaseConfiguration();

  private SqlConnect() {}

  public static Connection initConnection() {
    try {
      DataSource dataSource = getDataSource();
      if (conn == null) {
        conn = dataSource.getConnection();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public static DataSource getDataSource() {
    return databaseConfiguration.getDatasource();
  }
}
