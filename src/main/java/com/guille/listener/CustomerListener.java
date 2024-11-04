package com.guille.listener;

import jakarta.persistence.PrePersist;
import java.time.LocalDate;

public class CustomerListener {

  @PrePersist
  public LocalDate createdAt() {
    return LocalDate.now();
  }
}
