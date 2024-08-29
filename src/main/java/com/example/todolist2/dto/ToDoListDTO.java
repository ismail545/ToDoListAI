package com.example.todolist2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListDTO {
    private long id;
    private String title;
    private String description;
    private int priority;
    private boolean completed;
    private Date createdAt;
}
