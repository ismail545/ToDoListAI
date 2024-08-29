package com.example.todolist2.mapper;

import com.example.todolist2.domain.ToDoList;
import com.example.todolist2.dto.ToDoListDTO;
import org.springframework.stereotype.Component;

@Component
public class ToDoListMapper {

    public ToDoListDTO toDto(ToDoList toDoList) {
        return ToDoListDTO.builder()
                .id(toDoList.getId())
                .title(toDoList.getTitle())
                .description(toDoList.getDescription())
                .priority(toDoList.getPriority())
                .completed(toDoList.isCompleted())
                .createdAt(toDoList.getCreatedAt())
                .build();
    }

    public ToDoList toEntity(ToDoListDTO toDoListDTO) {
        return ToDoList.builder()
                .id(toDoListDTO.getId())
                .title(toDoListDTO.getTitle())
                .description(toDoListDTO.getDescription())
                .priority(toDoListDTO.getPriority())
                .completed(toDoListDTO.isCompleted())
                .createdAt(toDoListDTO.getCreatedAt())
                .build();
    }
}
