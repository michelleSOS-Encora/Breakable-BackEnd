package com.example.Breakable_Toy.Service.impl;

import com.example.Breakable_Toy.Service.ToDoService;
import com.example.Breakable_Toy.dto.AverageTimeMetricsDTO;
import com.example.Breakable_Toy.dto.TodoDto;
import com.example.Breakable_Toy.entity.Todo;
import com.example.Breakable_Toy.mapper.TodoMapper;
import com.example.Breakable_Toy.repository.ToDoRepository;
import com.example.Breakable_Toy.Exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    private ToDoRepository toDoRepository;

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = TodoMapper.mapToTodo(todoDto);
        Todo savedTodo = toDoRepository.save(todo);

        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto getToDoById(Long id) {
        Todo todo = toDoRepository.findById(id);
        if (todo == null) {
            throw new ResourceNotFoundException("Task not found");
        }
        return TodoMapper.mapToTodoDto(todo);
    }

    @Override
    public List<TodoDto> callTodos() {
        List<Todo> todos = toDoRepository.findAll();
        return todos.stream().map(TodoMapper::mapToTodoDto).collect(Collectors.toList());
    }


    @Override
    public TodoDto updateTodo(Long id, TodoDto updatedDto) {
        Todo todo = toDoRepository.findById(id);
        if (todo == null) throw new ResourceNotFoundException("Task not found!");
        todo.setText(updatedDto.getText());
        //todo.setDone(updatedDto.);
        todo.setDueDate(updatedDto.getDueDate());
        todo.setPriority(updatedDto.getPriority().toString());
        todo.setDone(updatedDto.isDone());
        Todo updatedDtoObj = toDoRepository.save(todo);
        return TodoMapper.mapToTodoDto(updatedDtoObj);
    }

    @Override
    public List<TodoDto> filterTodos(String text, String priority, Boolean done) {
        List<Todo> todos = toDoRepository.findAll();

        return todos.stream()
                .filter(todo -> (text == null || todo.getText().contains(text)) &&
                        (priority == null || todo.getPriority().equals(priority)) &&
                        (done == null || todo.isDone() == done))
                .map(TodoMapper::mapToTodoDto)
                .collect(Collectors.toList());
    }

    @Override
    public AverageTimeMetricsDTO calculateAverageTimeMetrics(String priority) {
        return null;
    }


    @Override
    public void deleteTodo(Long id) {
        Todo todo = toDoRepository.findById(id);
        if (todo == null) throw new ResourceNotFoundException("Task not found!");

        toDoRepository.delete(todo.getId());
    }

    @Override
    public ResponseEntity<Void> markAsDoneOrUndone(Long id, boolean done) {
        Optional<Todo> toDoOpt = Optional.ofNullable(toDoRepository.findById(id));
        if (toDoOpt.isPresent()) {
            Todo toDo = toDoOpt.get();
            toDo.setDone(done);//
            // toDo.setDone(true);
            //  toDoRepository.save(toDo);
            toDoRepository.update(toDo, true);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

// METRICS
}
