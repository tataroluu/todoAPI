package com.bilgeadam.todoAPI.service;

import com.bilgeadam.todoAPI.model.Users;
import com.bilgeadam.todoAPI.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UsersService {


    private UsersRepository usersRepository;

    public List<Users> getAll(){
        return usersRepository.getAll();
    }
}
