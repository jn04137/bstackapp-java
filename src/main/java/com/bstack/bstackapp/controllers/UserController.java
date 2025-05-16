package com.bstack.bstackapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.bstack.bstackapp.models.UserAccount;
import com.bstack.bstackapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/public/users")
    public List<UserAccount> getUsers() {
        Iterable<UserAccount> usersIterable = userRepo.findAll();
        List<UserAccount> userList = new ArrayList<>();
        usersIterable.forEach(user -> userList.add(nullifyEmail(user)));
        return userList;
    }

    private UserAccount nullifyEmail(UserAccount user) {
        user.setEmail(null);
        return user;
    }
}
