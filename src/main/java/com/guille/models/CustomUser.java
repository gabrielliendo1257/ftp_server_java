package com.guille.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.User;

/**
 * CustomUser
 */
public class CustomUser implements User {

  private Entity entity;

  public CustomUser(Entity entity) {
    this.entity = entity;
  }

  @Override
  public String getName() {
    return this.entity.getUsername();
  }

  @Override
  public String getPassword() {
    return this.entity.getPassword();
  }

  @Override
  public List<? extends Authority> getAuthorities() {
    return this.entity.getAuthorities();
  }

  @Override
  public List<? extends Authority> getAuthorities(Class<? extends Authority> clazz) {
    List<Authority> selectedAuthorities = new ArrayList<>();
    for (Authority authority : this.entity.getAuthorities()) {
      if (clazz.isInstance(authority)) {
        selectedAuthorities.add(authority);
      }
    }
    return selectedAuthorities;
  }

  @Override
  public AuthorizationRequest authorize(AuthorizationRequest request) {
    for (Authority authority : this.entity.getAuthorities()) {
      AuthorizationRequest authorizationRequest = authority.authorize(request);
      if (authorizationRequest != null) {
        return authorizationRequest;
      }
    }
    return null;
  }

  @Override
  public int getMaxIdleTime() {
    return this.entity.getMaxIdleTimeSec();
  }

  @Override
  public boolean getEnabled() {
    return this.entity.isEnabled();
  }

  @Override
  public String getHomeDirectory() {
    return this.entity.getHomeDir();
  }

}
