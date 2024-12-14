package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends JpaRepository<Task, Long> {
    long countByCompleted(boolean completed);

}
