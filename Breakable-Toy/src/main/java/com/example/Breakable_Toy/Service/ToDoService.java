package com.example.Breakable_Toy.Service;
import com.example.Breakable_Toy.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface ToDoService {
    TodoDto createTodo(TodoDto todoDto);
    TodoDto getToDoById(Long id);
    List<TodoDto> callTodos();
    TodoDto updateTodo(Long id, TodoDto updatedDto);

    AverageTimeMetricsDTO calculateAverageTimeMetrics(String priority);

    void deleteTodo(Long id);
    ResponseEntity<Void> markAsDoneOrUndone(Long id, boolean done);
 //   PaginatedResponse getTodosPagination(int page, int size, String sortBy, String sortDirection, String priority, Boolean done, String name);
    public List<TodoDto> filterTodos(String text, String priority, Boolean done);
}
