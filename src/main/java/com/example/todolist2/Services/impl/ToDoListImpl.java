package com.example.todolist2.Services.impl;


import com.example.todolist2.Repositories.ToDoListRepo;
import com.example.todolist2.Services.ToDoListService;
import com.example.todolist2.domain.ToDoList;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ToDoListImpl implements ToDoListService {
    private ToDoListRepo repo;

    public ToDoListImpl(ToDoListRepo repo) {
        this.repo = repo;
    }
    @Override
    public ToDoList findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("ToDoList with ID " + id + " not found"));
    }

    @Override
    public Iterable<ToDoList> findAllById(Iterable<Long> ids) {
        return repo.findAllById(ids);
    }

    @Override
    public ToDoList save(ToDoList toDoList) {
        return repo.save(toDoList);
    }

    @Override
    public Iterable<ToDoList> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("ToDoList with ID " + id + " not found");
        }
    }


    @Override
    public ToDoList fullUpdateToDoListById(Long id, ToDoList toDoList) {
        if (repo.existsById(id)){
            toDoList.setId(id);
            return repo.save(toDoList);
        }
        return null;
    }

    @Override
    public ToDoList updateToDoListById(Long id, ToDoList toDoList) {
        return repo.findById(id)
                .map(existingToDoList -> {
                    if (toDoList.getTitle() != null) {
                        existingToDoList.setTitle(toDoList.getTitle());
                    }
                    if (toDoList.getDescription() != null) {
                        existingToDoList.setDescription(toDoList.getDescription());
                    }
                    if (toDoList.getPriority() != 0) {
                        existingToDoList.setPriority(toDoList.getPriority());
                    }
                    if (toDoList.isCompleted()) {
                        existingToDoList.setCompleted(true);
                    }

                    return repo.save(existingToDoList);
                })
                .orElseThrow(() -> new RuntimeException("ToDoList not found"));
    }

}
