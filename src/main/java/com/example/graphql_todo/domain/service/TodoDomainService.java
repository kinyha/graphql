package com.example.graphql_todo.domain.service;

import com.example.graphql_todo.domain.Todo;
import com.example.graphql_todo.domain.sot.TodoSot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoDomainService {
    private final TodoSot todoSot;

    @Transactional(readOnly = true)
    public List<Todo> getAllTodos() {
        return todoSot.findAll();
    }

    @Transactional(readOnly = true)
    public Todo getTodoById(String id) {
        return todoSot.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found: " + id));
    }

    @Transactional
    public Todo createTodo(String title) {
        // Валидация
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        Todo todo = Todo.builder()
                .title(title.trim())
                .completed(false)
                .build();

        return todoSot.save(todo);
    }

    @Transactional
    public Todo toggleTodoStatus(String id) {
        Todo todo = getTodoById(id);
        todo.setCompleted(!todo.isCompleted());
        return todoSot.save(todo);
    }

    @Transactional
    public Todo updateTodo(String id, String title, Boolean completed) {
        Todo todo = getTodoById(id);

        if (title != null && !title.isBlank()) {
            todo.setTitle(title.trim());
        }

        if (completed != null) {
            todo.setCompleted(completed);
        }

        return todoSot.save(todo);
    }

    @Transactional
    public boolean deleteTodo(String id) {
        todoSot.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public List<Todo> getFilteredTodos(Boolean completed, String search) {
        return todoSot.findFiltered(completed, search);
    }
}