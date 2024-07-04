package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCompletedFalse();
    @Query("SELECT t FROM Task t ORDER BY t.due_Date ASC")
    List<Task> findAllByOrderByDueDateAsc();

    List<Task> findAllByOrderByPriorityAsc();
    @Query("SELECT t FROM Task t WHERE t.priority = :priority")
    List<Task> findTasksByPriority(@Param("priority") String priority);
    List<Task> findByCompleted(boolean completed);

//    @Query("SELECT c from Category ")
//    List<Task> findAllByCategory();
}
