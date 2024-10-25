package com.example.Breakable_Toy.repository;

import com.example.Breakable_Toy.dto.TodoDto;
import com.example.Breakable_Toy.entity.Todo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class ToDoRepository /*extends JpaRepository<Todo, Long>*/{
    private Map<Long, Todo> toDoStore = new ConcurrentHashMap<>();
    private long currentId = 0;

    public List<Todo> findAll() {
        return toDoStore.values().stream().collect(Collectors.toList());
    }

    public Todo save(Todo todo) {

            todo.setId(++currentId);

        toDoStore.put(todo.getId(), todo);
        return todo;
    }

    public Todo findById(Long id) {
        return toDoStore.get(id);
    }

    public void delete(Long id) {
        toDoStore.remove(id);
    }
    public Todo update(Todo updatedTodo, boolean isMarkDoneOrUndone) {
        Todo toDoToUpdate = toDoStore.get(updatedTodo.getId());
        toDoToUpdate.setDone(updatedTodo.isDone());
        if (isMarkDoneOrUndone) {
            return toDoToUpdate;
        }
        toDoToUpdate.setText(updatedTodo.getText());
        toDoToUpdate.setPriority(updatedTodo.getPriority());
        toDoToUpdate.setCreationDate(updatedTodo.getCreationDate());
        toDoToUpdate.setDueDate(updatedTodo.getDueDate());
        toDoToUpdate.setDoneDate(updatedTodo.getDoneDate());
        return toDoToUpdate;
    }
    public List<Todo> filter(String text, String priority, Boolean done) {
        return toDoStore.values().stream()
                .filter(todo -> (text == null || todo.getText().toLowerCase().contains(text.toLowerCase())) &&
                        (priority == null || todo.getPriority().equalsIgnoreCase(priority)) &&
                        (done == null || todo.isDone() == done))
                .collect(Collectors.toList());
    }


}
