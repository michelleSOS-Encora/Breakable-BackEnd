package com.example.Breakable_Toy.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.Breakable_Toy.entity.Priority;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;

    private String text;
    private String priority;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdAt;
    private boolean done;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date completedAt;


    public TodoDto(Long id, String text, Date createdAt, Date dueDate, boolean done, String priority, Date completedAt) {

        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.done = done;
        this.priority = priority;
        this.completedAt = completedAt;

    }

    public TodoDto(Long id, String text, Date creationDate, Date dueDate, boolean done, String priority) {
        this.id = id;
        this.text = text;
        this.createdAt = creationDate;
        this.dueDate = dueDate;
        this.done = done;
        this.priority = priority;
        this.completedAt = completedAt;
    }
}
