
package com.example.Breakable_Toy.mapper;
import com.example.Breakable_Toy.dto.TodoDto;
import com.example.Breakable_Toy.entity.Todo;

public class TodoMapper {
    public static TodoDto mapToTodoDto(Todo todo){
        return new TodoDto(
                todo.getId(),
                todo.getText(),
                todo.getCreationDate(),
                todo.getDueDate(),
                todo.isDone(),
                todo.getPriority()
        );
    }
    public static Todo mapToTodo(TodoDto tododto){
        return new Todo(
                tododto.getId(),
                tododto.getText(),
                tododto.getCreatedAt(),
                tododto.getDueDate(),
                tododto.isDone(),
                tododto.getPriority(),
                tododto.getCompletedAt()
        );

    }
}
