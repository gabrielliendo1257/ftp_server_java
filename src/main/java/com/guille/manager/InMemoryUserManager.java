package com.guille.manager;

import java.util.List;

import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import com.guille.models.CustomUser;
import com.guille.models.Entity;

/**
 * InMemoryUserManager
 */
public class InMemoryUserManager {

  private PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
  public UserManager userManager = this.userManagerFactory.createUserManager();

  public void save(List<Entity> users) throws FtpException {

    users.stream().forEach(user -> {
      CustomUser customUser = new CustomUser(user);
      try {
        this.userManager.save(customUser);
      } catch (FtpException e) {
        e.printStackTrace();
      }
    });

    String[] usernames = this.userManager.getAllUserNames();
    for (String username : usernames) {
      User user = this.userManager.getUserByName(username);
      System.out.println("Usuario: " + user.getName());
      System.out.println("Directorio de inicio: " + user.getHomeDirectory());
      System.out.println("Permisos: " + user.getAuthorities());
    }
  }

}
