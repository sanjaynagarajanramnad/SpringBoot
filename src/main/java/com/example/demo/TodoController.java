package com.example.demo;

import com.example.demo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;
        @PostMapping("/Create")
        ResponseEntity<Todo>  CreateUser(Todo todo){
            return new ResponseEntity<>(todoService.Create(todo), HttpStatus.CREATED);
        }
        @GetMapping("/list")
        public ResponseEntity<List<Todo>> getAllTodos() {
             return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
        }
        @DeleteMapping("/del/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
             todoService.del(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        @PutMapping("/put/{id}")
        public ResponseEntity<Todo> put( @PathVariable Integer id,Todo updatedTodo) {
            return  new ResponseEntity<>(todoService.update(id, updatedTodo), HttpStatus.OK);
        }
    }



