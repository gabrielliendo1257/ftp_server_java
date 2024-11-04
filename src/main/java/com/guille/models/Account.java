package com.guille.models;

import com.guille.models.persist.Authorities;
import com.guille.models.persist.Customer;
import java.util.ArrayList;
import java.util.List;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.impl.WritePermission;

/**
 * CustomUser
 */
public class Account implements User {

  private int maxIdleTimeSec = 0;
  private Customer customer;

  public Account() {}

  public Account(Customer customer) { this.customer = customer; }

  @Override
  public String getName() {
    return this.customer.getUsername();
  }

  @Override
  public String getPassword() {
    return this.customer.getPassword();
  }

  @Override
  public List<? extends Authority> getAuthorities() {
    List<Authority> selected = new ArrayList<>();
    for (Authorities authorities : this.customer.getAuthorities()) {
      if (authorities.getPermissions().toString().equalsIgnoreCase("write"))
        selected.add(new WritePermission());
    }
    return selected;
  }

  @Override
  public List<? extends Authority>
  getAuthorities(Class<? extends Authority> clazz) {
    List<Authority> selectedAuthorities = new ArrayList<>();
    for (Authority authority : this.getAuthorities()) {
      if (clazz.isInstance(authority)) {
        selectedAuthorities.add(authority);
      }
    }
    return selectedAuthorities;
  }

  @Override
  public AuthorizationRequest authorize(AuthorizationRequest request) {
    for (Authority authority : this.getAuthorities()) {
      AuthorizationRequest authorizationRequest = authority.authorize(request);
      if (authorizationRequest != null) {
        return authorizationRequest;
      }
    }
    return null;
  }

  @Override
  public int getMaxIdleTime() {
    return this.maxIdleTimeSec;
  }

  @Override
  public boolean getEnabled() {
    return this.customer.getIsEnabed();
  }

  @Override
  public String getHomeDirectory() {
    return this.customer.getHomeDir();
  }
}
