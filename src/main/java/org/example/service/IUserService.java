package org.example.service;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    void deleteUserid(Long id);
    User getUser(String email);
    public User get(Long id);
}
