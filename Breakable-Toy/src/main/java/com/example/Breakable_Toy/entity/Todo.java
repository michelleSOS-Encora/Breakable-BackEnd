package com.example.Breakable_Toy.entity;

import java.util.Date;
import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class Todo {
    @Id
    private Long id;
    private String text;
    private String priority;  // HIGH, MEDIUM, LOW
    private Date dueDate;
    private boolean done;
    private Date creationDate;
    private Date doneDate;

    //  constructor with
    public Todo(Long id, String text, String priority, Date dueDate, boolean done) {
        this.id = id;
        this.text = text;
        this.priority = priority;
        this.dueDate = dueDate;
        this.done = done;
        this.creationDate = new Date();
        this.doneDate = new Date();
    }
//constructor with all the parameters
public Todo(Long id, String text, String priority,Date creationDate, Date dueDate, boolean done) {
    this.id = id;
    this.text = text;
    this.priority = priority;
    this.dueDate = dueDate;
    this.done = done;
    this.creationDate = creationDate;
}




    public Todo(Long id, String text, Date createdAt, Date dueDate, boolean done, String priority, Date completedAt) {
        this.id = id;
        this.text = text;
        this.priority = priority;
        this.dueDate = dueDate;
        this.done = done;
        this.creationDate = createdAt;
        this.doneDate = completedAt;
    }
}
