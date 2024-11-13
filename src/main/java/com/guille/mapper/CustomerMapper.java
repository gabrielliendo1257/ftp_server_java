package com.guille.mapper;

import com.guille.connection.database.SqlConnect;
import com.guille.models.persist.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {

  private Connection conn = SqlConnect.initConnection();

  private String tableCustomer = "CREATE TABLE IF NOT EXISTS customer (id "
                                 + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                                 + "username, password, home_dir, "
                                 + "created_at)";
  private String statementSql =
      "INSERT INTO customer (id, username, password, home_dir, created_at) "
      + "VALUES (?, ?, ?, ?, ?)";
  private String queryCustomer =
      "SELECT id, username, password, home_dir, created_at FROM customer";
  private String queryGetCustomerById = "SELECT * FROM customer WHERE "
                                        + "id = ?";

  public void createTablaCustomer() {
    try {
      var statement = this.conn.createStatement();
      statement.execute(this.tableCustomer);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("Tabla creada exitosamente.");
  }

  public void saveCustomer(Customer customer) throws SQLException {
    var statement = this.conn.prepareStatement(this.statementSql);
    System.out.println("Guardando Customer: " + customer.toString());
    statement.setString(2, customer.getUsername());
    statement.setString(3, customer.getPassword());
    statement.setString(4, customer.getHomeDir());
    statement.setString(5, customer.getCreatedAt());
    statement.executeUpdate();
    System.out.println("Datos insertados exitosamente.");
  }

  public Customer findCustomerById(Integer id) throws SQLException {
    var statement = this.conn.prepareStatement(this.queryGetCustomerById);
    statement.setInt(1, id);

    return this.setterEntity(statement.executeQuery());
  }

  public void recoveryData() throws SQLException {
    var statement = this.conn.createStatement();
    ResultSet resultSet = statement.executeQuery(this.queryCustomer);

    while (resultSet.next()) {
      System.out.printf(
          "%-5s%-10s%-20s%-10s%-10s%n", resultSet.getInt("id"),
          resultSet.getString("username"), resultSet.getString("password"),
          resultSet.getString("home_dir"), resultSet.getString("created_at"));
    }
  }

  private Customer setterEntity(ResultSet rs) throws SQLException {
    Integer id = rs.getInt("id");
    String username = rs.getString("username");
    String password = rs.getString("password");
    String homDir = rs.getString("home_dir");
    String createdAt = rs.getString("created_at");

    Customer customer = new Customer(username, password, homDir);
    customer.setId(id);
    customer.setCreatedAt(createdAt);

    return customer;
  }
}
