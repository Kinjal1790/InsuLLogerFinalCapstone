package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByEmail(String email);

    int findIdByEmail(String email);

    boolean create(String firstName, String lastName, String email, String password, String role);
}
