package com.guille.models;

import java.util.List;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.User;

/**
 * CustomUser
 */
public class CustomUser implements User {

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getName'");
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
  }

  @Override
  public List<? extends Authority> getAuthorities() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
  }

  @Override
  public List<? extends Authority> getAuthorities(Class<? extends Authority> clazz) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
  }

  @Override
  public AuthorizationRequest authorize(AuthorizationRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'authorize'");
  }

  @Override
  public int getMaxIdleTime() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getMaxIdleTime'");
  }

  @Override
  public boolean getEnabled() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEnabled'");
  }

  @Override
  public String getHomeDirectory() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHomeDirectory'");
  }

}
