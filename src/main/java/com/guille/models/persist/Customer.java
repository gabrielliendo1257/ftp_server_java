package com.guille.models.persist;

import com.guille.listener.CustomerListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@ToString
@Entity
@Table(name = "customers")
@EntityListeners(CustomerListener.class)
@Data
public class Customer {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

  private String username;

  private String password;

  private String homeDir = null;

  private LocalDate createdAt;

  private Boolean isEnabed = true;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
             orphanRemoval = true)
  @JoinColumn(name = "id_customer")
  private List<Authorities> authorities;

  public Customer(String username, String password, String homeDir,
                  List<Authorities> authorities) {
    this.username = username;
    this.password = password;
    this.homeDir = homeDir;
    this.authorities = authorities;
  }
}
