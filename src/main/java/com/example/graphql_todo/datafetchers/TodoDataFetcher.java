package com.example.graphql_todo.datafetchers;

import com.example.graphql_todo.domain.mapper.TodoMapper;
import com.example.graphql_todo.domain.service.TodoDomainService;
import com.example.graphql_todo.generated.types.CreateTodoInput;
import com.example.graphql_todo.generated.types.Todo;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class TodoDataFetcher {
    private final TodoDomainService domainService;
    private final TodoMapper mapper;

    @DgsQuery
    public List<Todo> todos() {
        return domainService.getAllTodos().stream()
                .map(mapper::toGraphQL)
                .toList();
    }

    @DgsQuery
    public Todo todo(@InputArgument String id) {
        return mapper.toGraphQL(domainService.getTodoById(id));
    }

    @DgsMutation
    public Todo createTodo(@InputArgument CreateTodoInput input) {
        var created = domainService.createTodo(input.getTitle());
        return mapper.toGraphQL(created);
    }

    @DgsMutation
    public Todo toggleTodo(@InputArgument String id) {
        return mapper.toGraphQL(domainService.toggleTodoStatus(id));
    }

    @DgsMutation
    public Boolean deleteTodo(@InputArgument String id) {
        return domainService.deleteTodo(id);
    }
}