package com.mazowiecka.demo.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    Long taskId;

    String description;

    String priority;

    LocalDate due_Date;

    boolean completed = false;

    @Column(name = "dynamic_priority")
    String dynamicPriority;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Task(String description, String priority, LocalDate due_Date, boolean completed) {
        this.description = description;
        this.priority = priority;
        this.due_Date = due_Date;
        this.completed = completed;
    }

}
