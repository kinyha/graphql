package com.example.graphql_todo.datafetchers;

import com.example.graphql_todo.generated.types.CreateTodoInput;
import com.example.graphql_todo.generated.types.Todo;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.time.Instant;
import java.util.List;

// datafetchers/TodoDataFetcher.java
@DgsComponent
public class TodoDataFetcher {

    @DgsQuery
    public List<Todo> todos() {
        return List.of();  // пока заглушка
    }

    @DgsQuery
    public Todo todo(@InputArgument String id) {
        return null;
    }

    @DgsMutation
    public Todo createTodo(@InputArgument CreateTodoInput input) {
        return Todo.newBuilder()
                .id("1")
                .title(input.getTitle())
                .completed(false)
                .createdAt(Instant.now().toString())
                .build();
    }
}
