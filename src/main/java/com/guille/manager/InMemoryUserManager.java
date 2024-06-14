package com.guille.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.impl.BaseUser;

/**
 * InMemoryUserManager
 */
public class InMemoryUserManager {

  private Map<String, User> inMemoryUsers = new HashMap<>();

  public void save(List<User> users) {
    users.stream().forEach(user -> {
      this.inMemoryUsers.put(user.getName(), user);
    });
  }

  public void save(User user) {
    this.inMemoryUsers.put(user.getName(), user);
    BaseUser baseUser = new BaseUser();
  }
}
