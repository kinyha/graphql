package com.example.graphql_todo.domain.sot;

import com.example.graphql_todo.domain.Todo;
import com.example.graphql_todo.domain.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TodoSot {
    private final TodoRepository repository;

    public List<Todo> findAll() {
        log.debug("Fetching all todos");
        return repository.findAll();
    }

    public Optional<Todo> findById(Long id) {
        log.debug("Fetching todo by id: {}", id);
        return repository.findById(id);
    }

    public Todo save(Todo todo) {
        log.debug("Saving todo: {}", todo);
        return repository.save(todo);
    }

    public void deleteById(Long id) {
        log.debug("Deleting todo by id: {}", id);
        repository.deleteById(id);
    }

    // Фильтрация
    public List<Todo> findFiltered(Boolean completed, String search) {
        var todos = repository.findAll();

        if (completed != null) {
            todos = todos.stream()
                    .filter(t -> t.isCompleted() == completed)
                    .toList();
        }

        if (search != null && !search.isBlank()) {
            final String searchLower = search.toLowerCase();
            todos = todos.stream()
                    .filter(t -> t.getTitle().toLowerCase().contains(searchLower))
                    .toList();
        }

        return todos;
    }

    public long count() {
        return repository.count();
    }
}