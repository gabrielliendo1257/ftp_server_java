package com.guille.models.persist;

import com.guille.enums.Permissions;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Authorities {

  private Integer id;

  private Permissions permissions;

  private Integer idCustomer;

  public Authorities(Permissions permissions) {
    this.permissions = permissions;
  }
}
