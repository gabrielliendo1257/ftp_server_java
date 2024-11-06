package com.guille.repository;

import com.guille.models.persist.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository
    extends JpaRepository<Authorities, Integer> {}
