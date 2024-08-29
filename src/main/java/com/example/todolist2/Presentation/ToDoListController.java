package com.example.todolist2.Presentation;

import com.example.todolist2.Services.ToDoListService;
import com.example.todolist2.domain.ToDoList;
import com.example.todolist2.dto.ToDoListDTO;
import com.example.todolist2.mapper.ToDoListMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Log
public class ToDoListController {

    private final ToDoListService todolistService;
    private final ToDoListMapper toDoListMapper;

    @Autowired
    public ToDoListController(ToDoListService todolistService, ToDoListMapper toDoListMapper) {
        this.todolistService = todolistService;
        this.toDoListMapper = toDoListMapper;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Controller is working!";
    }

    @GetMapping(path = "/todolist")
    public List<ToDoListDTO> getAllToDoList() {
        return StreamSupport.stream(todolistService.findAll().spliterator(), false)
                .map(toDoListMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/todolist/{id}")
    public ToDoListDTO getToDoListById(@PathVariable long id) {
        ToDoList toDoList = todolistService.findById(id);
        return toDoListMapper.toDto(toDoList);
    }

    @GetMapping(path = "/todolist/ids/{ids}")
    public List<ToDoListDTO> getAllToDoListByIds(@PathVariable String ids) {
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        return StreamSupport.stream(todolistService.findAllById(idList).spliterator(), false)
                .map(toDoListMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/todolist")
    public ToDoListDTO createToDoList(@RequestBody ToDoListDTO toDoListDTO) {
        ToDoList toDoList = toDoListMapper.toEntity(toDoListDTO);
        ToDoList savedToDoList = todolistService.save(toDoList);
        return toDoListMapper.toDto(savedToDoList);
    }

    @PutMapping(path = "/todolist/{id}")
    public ToDoListDTO fullupdateToDoListById(@PathVariable("id") Long id, @RequestBody ToDoListDTO todolistDTO) {
        ToDoList toDoList = toDoListMapper.toEntity(todolistDTO);
        ToDoList updatedToDoList = todolistService.fullUpdateToDoListById(id, toDoList);
        return toDoListMapper.toDto(updatedToDoList);
    }

    @PatchMapping(path = "/todolist/{id}")
    public ToDoListDTO updateToDoListById(@PathVariable("id") Long id, @RequestBody ToDoListDTO toDoListDTO) {
        ToDoList toDoList = toDoListMapper.toEntity(toDoListDTO);
        ToDoList updatedToDoList = todolistService.updateToDoListById(id, toDoList);
        return toDoListMapper.toDto(updatedToDoList);
    }

    @DeleteMapping(path = "/todolist/{id}")
    public ResponseEntity<Void> deleteToDoListById(@PathVariable("id") Long id) {
        todolistService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
