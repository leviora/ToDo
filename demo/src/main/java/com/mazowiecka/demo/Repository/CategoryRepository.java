package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(String categoryName);

    Optional<Category> findByCategoryName(String categoryName);

}




