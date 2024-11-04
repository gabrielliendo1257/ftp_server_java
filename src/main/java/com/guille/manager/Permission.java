package com.guille.manager;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;

public class Permission implements Authority {

  @Override
  public boolean canAuthorize(AuthorizationRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'canAuthorize'");
  }

  @Override
  public AuthorizationRequest authorize(AuthorizationRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'authorize'");
  }
}
