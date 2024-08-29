package com.example.todolist2.Repositories;

import com.example.todolist2.domain.ToDoList;
import org.springframework.data.repository.CrudRepository;

public interface ToDoListRepo extends CrudRepository<ToDoList, Long> {
}
