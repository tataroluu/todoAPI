package com.bilgeadam.todoAPI.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private Long ID;

    private Long userId;

    private String todo;

    private String dateTime;

    private Long priority;

    private Boolean inProgress;

    private Boolean done;
}
