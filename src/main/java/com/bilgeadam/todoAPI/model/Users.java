package com.bilgeadam.todoAPI.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private Long ID;

    private String userName;

    private String password;
    private Long roleId;
}
