package com.example.Breakable_Toy.controller;
import com.example.Breakable_Toy.dto.PaginatedResponse;
import com.example.Breakable_Toy.dto.TodoDto;
import com.example.Breakable_Toy.entity.Todo;
import com.example.Breakable_Toy.Service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin("http://localhost:8083")
@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class ToDoController {
@Autowired
    private final ToDoService service;

    //api
    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo=service.createTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    //bout get rest api
    @GetMapping("{id}")
    public ResponseEntity<TodoDto>getTodoById(@PathVariable("id") Long id){
        //  Long id;
        TodoDto todoDto=service.getToDoById(id);
        return ResponseEntity.ok(todoDto);
    }
    //api to get all the todos
    @GetMapping
    public ResponseEntity<List<TodoDto>> callTodos(){
        List<TodoDto>todos=service.callTodos();
        return ResponseEntity.ok(todos);
    }

    // update todo rest api
    @PutMapping("/api/todos{id}")
    public ResponseEntity <TodoDto> updateTodo(@PathVariable("id") Long id,
                                               @RequestBody  TodoDto updatedTodo){
        TodoDto todoDto= service.updateTodo(id,updatedTodo);
        // return ResponseEntity.ok(Collections.singletonList(todoDto));
        return ResponseEntity.ok(todoDto);
    }
    //build delete rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteTodo(@PathVariable("id") Long id){
        service.deleteTodo(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

//update as done
@PutMapping("/{id}/done")
public ResponseEntity<Void>  markAsDone(@PathVariable Long id) {
    return service.markAsDoneOrUndone(id, true);
}
    //update as not finished
    @PutMapping("/{id}/undone")
    public ResponseEntity<Void>  markAsUndone(@PathVariable Long id) {
        return service.markAsDoneOrUndone(id, false);
    }

    @GetMapping("/filter/{text}-{priority}-{done}")
    public ResponseEntity<List<TodoDto>> filterTodos(
            @PathVariable("text") String text,
            @PathVariable("priority") String priority,
            @PathVariable("done") Boolean done) {

        List<TodoDto> filteredTodos = service.filterTodos(
                text.equals("null") ? null : text,
                priority.equals("null") ? null : priority,
                done
        );
        return ResponseEntity.ok(filteredTodos);
    }
}

