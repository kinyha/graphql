package com.example.graphql_todo.domain.mapper;

import com.example.graphql_todo.domain.Todo;
import com.example.graphql_todo.generated.types.CreateTodoInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    // Entity -> GraphQL
    @Mapping(target = "createdAt", expression = "java(todo.getCreatedAt().toString())")
    com.example.graphql_todo.generated.types.Todo toGraphQL(Todo todo);

    // Input -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", constant = "false")
    @Mapping(target = "createdAt", ignore = true)
    Todo toEntity(CreateTodoInput input);
}