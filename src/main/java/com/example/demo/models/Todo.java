package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    int id;
    String Title;
    String Description;
}
