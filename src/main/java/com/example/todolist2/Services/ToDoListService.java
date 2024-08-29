package com.example.todolist2.Services;

import com.example.todolist2.domain.ToDoList;
import org.springframework.stereotype.Service;

@Service
public interface ToDoListService {

    ToDoList save (ToDoList toDoList);
    Iterable<ToDoList> findAll();
    Iterable<ToDoList> findAllById(Iterable<Long> ids);
    ToDoList findById(Long id);
    void deleteById(Long id);
    ToDoList fullUpdateToDoListById(Long id, ToDoList todolist);
    ToDoList updateToDoListById(Long id, ToDoList toDoList);
}
