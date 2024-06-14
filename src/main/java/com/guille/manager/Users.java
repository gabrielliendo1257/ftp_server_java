package com.guille.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.impl.BaseUser;

public class Users implements User {
    private String name = null;
    private String password = null;
    private int maxIdleTimeSec = 0;
    private String homeDir = null;
    private boolean isEnabled = true;
    private List<? extends Authority> authorities = new ArrayList();

    @Override
    public AuthorizationRequest authorize(AuthorizationRequest request) {
        return null;
    }

    @Override
    public List<? extends Authority> getAuthorities() {
        return this.authorities != null ? Collections.unmodifiableList(this.authorities) : null;
    }

    @Override
    public List<? extends Authority> getAuthorities(Class<? extends Authority> clazz) {
        List<Authority> selected = new ArrayList<>();
        Iterator var3 = this.authorities.iterator();

        while(var3.hasNext()) {
            Authority authority = (Authority)var3.next();
            if (authority.getClass().equals(clazz)) {
                selected.add(authority);
            }
        }

      return selected;
    }

    @Override
    public boolean getEnabled() {
        return this.isEnabled;
    }

    @Override
    public String getHomeDirectory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHomeDirectory'");
    }

    @Override
    public int getMaxIdleTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxIdleTime'");
    }

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
    
}
