package com.example.graphql_todo.datafetchers;


import com.example.graphql_todo.domain.TodoBack;
import com.example.graphql_todo.domain.TodoRepository;
import com.example.graphql_todo.generated.types.CreateTodoInput;
import com.example.graphql_todo.generated.types.Todo;
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
    public List<Todo> todos() {
        return todoRepository.findAll().stream()
                .map(this::toGraphQL)
                .toList();
    }

    @DgsMutation
    public Todo createTodo(@InputArgument CreateTodoInput input) {
        var entity = todoRepository.save(
                TodoBack.builder()
                        .title(input.getTitle())
                        .completed(false)
                        .build()
        );
        return toGraphQL(entity);
    }

    private Todo toGraphQL(TodoBack entity) {
        return Todo.newBuilder()
                .id(entity.getId())
                .title(entity.getTitle())
                .completed(entity.isCompleted())
                .createdAt(entity.getCreatedAt().toString())
                .build();
    }
}
