package com.mazowiecka.demo.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    Long taskId;
    String description;
    String priority;
    LocalDate dueDate;
    boolean completed;
    String dynamicPriority;
    Long categoryId;
    Long projectId;
}
