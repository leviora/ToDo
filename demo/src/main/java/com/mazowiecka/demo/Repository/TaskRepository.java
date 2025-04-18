package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompletedFalse();

    @Query("SELECT t FROM Task t ORDER BY t.due_Date ASC")
    List<Task> findAllByOrderByDueDateAsc();

    @Query("SELECT t FROM Task t WHERE t.priority = :priority")
    List<Task> findTasksByPriority(@Param("priority") String priority);

    List<Task> findByCompleted(boolean completed);

    List<Task> findByCategory_CategoryId(Long categoryId);

    long countByCompleted(boolean completed);

    long countByPriority(String priority);

    List<Task> findAllByUser_Id(Long userId);

    List<Task> findByUser(User user);

    List<Task> findByProject(Project project);

}
