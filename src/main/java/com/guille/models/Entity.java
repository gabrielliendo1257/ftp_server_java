package com.guille.models;

import java.util.List;

import org.apache.ftpserver.ftplet.Authority;

/**
 * User
 */
public class Entity {

  private String username;
  private String password;
  private List<Authority> authorities;
  private int maxIdleTimeSec = 0;
  private String homeDir = null;
  private boolean isEnabled = true;

  public Entity() {
  }

  public Entity(String username, String password, List<Authority> authorities, String homeDir) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
    this.homeDir = homeDir;
  }

  public List<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getMaxIdleTimeSec() {
    return maxIdleTimeSec;
  }

  public String getHomeDir() {
    return homeDir;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

}
