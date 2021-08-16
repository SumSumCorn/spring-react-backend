package com.mg.todolist.service;

import com.mg.todolist.TodolistApplication;
import com.mg.todolist.domain.Todo;
import com.mg.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService{
    private final TodoRepository todoRepository;

    @Transactional
    public Long insertTodo(Todo todo) {
        todoRepository.save(todo);
        return todo.getId();
    }

    @Transactional
    public Todo updateTodo(Long todoId) {
        Todo todo = todoRepository.findOne(todoId);
        if (todo == null) {
            return new Todo();
        }

        todo.setCompleted(!todo.getCompleted());
        return todo;
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findOne(todoId);

        todoRepository.deleteOne(todo);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
}
