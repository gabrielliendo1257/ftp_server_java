package com.guille.service;

import com.guille.models.persist.Authorities;
import com.guille.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

  @Autowired private AuthoritiesRepository aRepository;

  public void saveAuthority(Authorities entity) {
    this.aRepository.save(entity);
  }

  public void deleteAuthority(Authorities entity) {
    this.aRepository.delete(entity);
  }

  public void deleteAuthorityById(Integer id) {
    this.aRepository.deleteById(id);
  }

  public Authorities getAutthorityById(Integer id) {
    return this.aRepository.getReferenceById(id);
  }
}
