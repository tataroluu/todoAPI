package com.bilgeadam.todoAPI.controller;

import com.bilgeadam.todoAPI.model.Users;
import com.bilgeadam.todoAPI.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "users")
public class UsersController {

    @Autowired
    public UsersRepository usersRepository;

    @GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> getAll() {
        // localhost:8080/users/getAll
        List<Users> liste = usersRepository.getAll();
        return liste;
    }
}
