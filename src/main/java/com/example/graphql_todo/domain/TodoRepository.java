package com.example.graphql_todo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// domain/TodoRepository.java
public interface TodoRepository extends JpaRepository<Todo, Long> {
}