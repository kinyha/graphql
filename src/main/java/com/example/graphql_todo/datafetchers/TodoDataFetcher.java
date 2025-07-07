package com.example.graphql_todo.datafetchers;


import com.example.graphql_todo.domain.Todo;
import com.example.graphql_todo.domain.TodoRepository;
import com.example.graphql_todo.generated.types.CreateTodoInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;

import java.util.List;

// datafetchers/TodoDataFetcher.java
@DgsComponent
@RequiredArgsConstructor
public class TodoDataFetcher {
    private final TodoRepository todoRepository;

    @DgsQuery
    public List<com.example.graphql_todo.generated.types.Todo> todos() {
        return todoRepository.findAll().stream()
                .map(this::toGraphQL)
                .toList();
    }

    @DgsMutation
    public com.example.graphql_todo.generated.types.Todo createTodo(@InputArgument CreateTodoInput input) {
        var entity = todoRepository.save(
                Todo.builder()
                        .title(input.getTitle())
                        .completed(false)
                        .build()
        );
        return toGraphQL(entity);
    }

    private com.example.graphql_todo.generated.types.Todo toGraphQL(Todo entity) {
        return com.example.graphql_todo.generated.types.Todo.newBuilder()
                .id(entity.getId())
                .title(entity.getTitle())
                .completed(entity.isCompleted())
                .createdAt(entity.getCreatedAt().toString())
                .build();
    }
}
