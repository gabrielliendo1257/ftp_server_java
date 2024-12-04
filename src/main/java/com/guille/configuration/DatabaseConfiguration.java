package com.guille.configuration;

import com.zaxxer.hikari.HikariDataSource;
import java.nio.file.Paths;
import javax.sql.DataSource;

public class DatabaseConfiguration {

  private final String url =
      "jdbc:sqlite:" + Paths.get("").toAbsolutePath().toString() +
      "/db/imp_data.db";

  public DataSource getDatasource() {

    HikariDataSource datasource = new HikariDataSource();
    datasource.setJdbcUrl(url);
    datasource.setUsername("admin");
    datasource.setPassword("admin");
    datasource.setMinimumIdle(2);
    datasource.setIdleTimeout(10000);
    datasource.setMaximumPoolSize(10000);
    datasource.setMaximumPoolSize(10);

    return datasource;
  }
}
