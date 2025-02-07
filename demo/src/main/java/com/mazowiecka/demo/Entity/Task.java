package com.mazowiecka.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
    private String description;
    private String priority;
    private LocalDate due_Date;
    private boolean completed = false;
    @Column(name = "dynamic_priority")
    private String dynamicPriority;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    public Task(Category category, String description) {
        this.category = category;
        this.description = description;
    }

    public Task(String description, String priority, LocalDate due_Date, boolean completed) {
        this.description = description;
        this.priority = priority;
        this.due_Date = due_Date;
        this.completed = false;
    }

    public Task() {

    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
