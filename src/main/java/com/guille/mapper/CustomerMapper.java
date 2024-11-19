package com.guille.mapper;

import com.guille.connection.database.SqlConnect;
import com.guille.models.persist.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerMapper {

  private Connection conn = SqlConnect.initConnection();

  private String tableCustomer = "CREATE TABLE IF NOT EXISTS customer (id "
                                 + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                                 + "username, password, home_dir, "
                                 + "created_at)";
  private String insertCustomer =
      "INSERT INTO customer (id, username, password, home_dir, created_at) "
      + "VALUES (?, ?, ?, ?, ?)";
  private String queryFindAllCustomers =
      "SELECT id, username, password, home_dir, created_at FROM customer";
  private String queryFindCustomerById =
      "SELECT id, username, password, home_dir, created_at FROM customer WHERE "
      + "id = ?";

  private String queryFindCustomerByUsername = "SELECT * FROM customer WHERE "
                                               + "username = ?";
  private String queryUpdateCustomerByUsername =
      "UPDATE customer SET username = ?, password = ?, "
      + "home_dir = ?, "
      + "created_at = ? WHERE username = ?";

  private String queryDeleteCustomerByUsername =
      "DELETE FROM customer WHERE username = ?";

  private String adminName = "admin";
  private String queryFindCustomerAdmin = "SELECT * FROM customer WHERE "
                                          + "username = admin";
  private String authenticateStatement =
      "SELECT password FROM customer WHERE username={username}";

  public void createTablaCustomer() {
    try {
      var statement = this.conn.createStatement();
      statement.execute(this.tableCustomer);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("Tabla creada exitosamente.");
  }

  public void saveCustomer(Customer customer) {

    if (!this.doesExist(customer.getUsername())) {
      System.out.println("El usuario con nombre " + customer.getUsername() +
                         " no existe.");
      try {
        var statement = this.conn.prepareStatement(this.insertCustomer);
        statement.setString(2, customer.getUsername());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getHomeDir());
        statement.setString(5, customer.getCreatedAt());

        statement.executeUpdate();
        System.out.println("Customer persistido con exito: " +
                           customer.toString());
      } catch (SQLException s) {
      }
      // this.dbUserManager.save(new Account(customer));
    } else {
      System.out.println("El usuario con nombre " + customer.getUsername() +
                         " ya existe.");
      return;
    }
  }

  public List<Customer> findAllCustomers() {

    List<Customer> customers = new ArrayList<>();

    try {
      var st = this.conn.createStatement();
      var resultSet = st.executeQuery(this.queryFindAllCustomers);
      while (resultSet.next()) {
        Integer id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String homeDir = resultSet.getString("home_dir");
        String createdAt = resultSet.getString("created_at");

        Customer customer = new Customer(username, password, homeDir);
        customer.setId(id);
        customer.setCreatedAt(createdAt);

        customers.add(customer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customers;
  }

  public Customer findCustomerById(Integer id) throws SQLException {
    var statement = this.conn.prepareStatement(this.queryFindCustomerById);
    statement.setInt(1, id);

    return this.setterEntity(statement.executeQuery());
  }

  public void recoveryData() {
    try {
      var statement = this.conn.createStatement();
      ResultSet resultSet = statement.executeQuery(this.queryFindAllCustomers);

      while (resultSet.next()) {
        System.out.printf(
            "%-5s%-10s%-20s%-10s%-10s%n", resultSet.getInt("id"),
            resultSet.getString("username"), resultSet.getString("password"),
            resultSet.getString("home_dir"), resultSet.getString("created_at"));
      }
    } catch (SQLException e) {
    }
  }

  public Optional<Customer> findCustomerByUsername(String username) {
    try {
      var pStatement =
          this.conn.prepareStatement(this.queryFindCustomerByUsername);
      pStatement.setString(1, username);
      var rs = pStatement.executeQuery();
      return Optional.of(this.setterEntity(rs));
    } catch (SQLException s) {
      return Optional.empty();
    }
  }

  private Boolean doesExist(String username) {
    return this.findCustomerByUsername(username).get().getUsername() == null
        ? false
        : true;
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
