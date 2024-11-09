package com.guille.models.persist;

import java.time.LocalDate;
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

  private LocalDate createdAt;

  private Boolean isEnabed = true;

  private List<Authorities> authorities;

  public Customer(String username, String password, String homeDir,
                  List<Authorities> authorities) {
    this.username = username;
    this.password = password;
    this.homeDir = homeDir;
    this.authorities = authorities;
  }
}
