package com.example.demo;

import com.example.demo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo Create(Todo todo){
        return todoRepository.save(todo);
    }
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    public void del(Integer id){
         todoRepository.deleteById(id);
    }

    public Todo update(Integer id, Todo updatedTodo) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        // Update fields
        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());

        return todoRepository.save(todo);
    }



}
