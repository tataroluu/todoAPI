package com.bilgeadam.todoAPI.controller;

import com.bilgeadam.todoAPI.model.Todo;
import com.bilgeadam.todoAPI.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "todo")
public class TodoController {
    @Autowired
    private TodoService toDoService;

    @GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8000")
    public List<Todo> getAll() {
        // localhost:8080/todo/getAll
        List<Todo> liste = toDoService.getAll();
        return liste;
    }


    @DeleteMapping(path = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8000")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        //http://localhost:8080/todo/delete/1
        boolean result = toDoService.remove(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(TodoService.message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(id + " failed to delete");
        }

    }

    @PutMapping(path = "guncelle/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8000")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Todo todo) {
        // http://localhost:8080/todo/guncelle/2
        boolean result = toDoService.update(todo, id);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body(TodoService.message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(id + " failed to update");
        }


    }

    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8000")
    public ResponseEntity<String> save(@RequestBody Todo todo) {
        // <http://localhost:8080/todo/save>
        boolean result = toDoService.save(todo);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body(TodoService.message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(todo.getID() + " failed to update");
        }

    }

}