package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Todo;

public interface TodoRepository extends  JpaRepository<Todo, Integer> {
}
