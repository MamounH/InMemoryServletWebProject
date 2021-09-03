package com.atypon.dbproject.dao;


import com.atypon.dbproject.entity.User;

import java.util.List;

public interface UserDao {

      User getUser(String username);
      void updateUser(User user, String email);
      List<User> getAll();
      User verifyCredentials(String login , String password);
      void deleteUser(String username);
      void createUser(User user) ;
      boolean userExists(String username);

    }
