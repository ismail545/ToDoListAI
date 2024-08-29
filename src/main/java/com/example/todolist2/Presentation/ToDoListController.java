package com.example.todolist2.Presentation;

import com.example.todolist2.Services.ToDoListService;
import com.example.todolist2.Services.impl.ToDoListImpl;
import com.example.todolist2.domain.ToDoList;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log
public class ToDoListController {

    private ToDoListService todolistService;

    @Autowired
    public ToDoListController(ToDoListService todolistService) {
        this.todolistService = todolistService;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Controller is working!";
    }

    @GetMapping(path = "/todolist")
    public Iterable<ToDoList> getAllToDoList() {
        return todolistService.findAll();
    }


    @GetMapping(path = "/todolist/{id}")
    public ToDoList getToDoListById(@PathVariable long id) {
        return todolistService.findById(id);
    }

    @GetMapping(path = "/todolist/ids/{ids}")
    public Iterable<ToDoList> getAllToDoListByIds(@PathVariable String ids) {
        // Split the comma-separated string into an array
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::valueOf) // Convert each to Long
                .collect(Collectors.toList()); // Collect as a list

        // Pass the list to the service
        return todolistService.findAllById(idList);
    }


    @PostMapping(path = "/todolist")
    public ToDoList createToDoList(@RequestBody ToDoList toDoList) {
        return todolistService.save(toDoList);
    }

    @PutMapping(path = "/todolist/{id}")
    public ToDoList fullupdateToDoListById(@PathVariable("id") Long id, @RequestBody ToDoList todolist ){
        todolistService.fullUpdateToDoListById(id, todolist);
        return todolist;
    }

    @PatchMapping(path = "/todolist/{id}")
    public ToDoList updateToDoListById(@PathVariable("id") Long id, @RequestBody ToDoList toDoList) {
        return todolistService.updateToDoListById(id, toDoList);

    }

    @DeleteMapping(path = "/todolist/{id}")
    public ResponseEntity<Void> deleteToDoListById(@PathVariable("id") Long id) {
        todolistService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}


