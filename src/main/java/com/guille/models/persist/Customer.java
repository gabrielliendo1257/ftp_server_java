package com.guille.models.persist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Customer {

  private Integer id;

  private String username;

  private String password;

  private String homeDir = null;

  private String createdAt = LocalDate.now().toString();

  private Boolean isEnabed = true;

  private List<Authorities> authorities = new ArrayList<>();

  public Customer(String username, String password, String homeDir) {
    this.username = username;
    this.password = password;
    this.homeDir = homeDir;
  }
}
