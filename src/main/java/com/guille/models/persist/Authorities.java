package com.guille.models.persist;

import com.guille.enums.Permissions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class Authorities {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

  @Enumerated(EnumType.STRING) private Permissions permissions;

  @Column(name = "id_customer") private Integer idCustomer;

  public Authorities(Permissions permissions) {
    this.permissions = permissions;
  }
}
