package com.guille.manager;

import com.guille.models.Account;
import com.guille.models.persist.Customer;
import java.util.List;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

/**
 * InMemoryUserManager
 */
public class CustomUserManager {

  private PropertiesUserManagerFactory userManagerFactory =
      new PropertiesUserManagerFactory();
  public UserManager userManager = this.userManagerFactory.createUserManager();

  public void save(List<Customer> users) {

    users.stream().forEach(user -> {
      BaseUser customUser = new BaseUser(new Account(user));
      try {
        this.userManager.save(customUser);
      } catch (FtpException e) {
        e.printStackTrace();
      }
    });

    try {
      String[] usernames = this.userManager.getAllUserNames();
      for (String username : usernames) {
        User user = this.userManager.getUserByName(username);
        System.out.println("Usuario: " + user.getName());
        System.out.println("Directorio de inicio: " + user.getHomeDirectory());
        System.out.println("Permisos: " + user.getAuthorities());
      }
    } catch (Exception e) {
    }
  }
}
