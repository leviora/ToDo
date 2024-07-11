//package com.mazowiecka.demo.Entity.Test;
//
//import com.mazowiecka.demo.Entity.Category;
//import com.mazowiecka.demo.Entity.Task;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TaskTest {
//    private Task task;
//    private Category category;
//
//    @BeforeEach
//    public void setUp() {
//        task = new Task();
//        category = new Category();
//        category.setCategoryId(1L);
//        category.setCategoryName("Test Category");
//    }
//
//    @Test
//    public void testSetAndGetTaskId() {
//        Long id = 1L;
//        task.setTaskId(id);
//        assertEquals(id, task.getTaskId());
//    }
//
//    @Test
//    public void testSetAndGetDescription() {
//        String description = "Test Task Description";
//        task.setDescription(description);
//        assertEquals(description, task.getDescription());
//    }
//
//    @Test
//    public void testSetAndGetPriority() {
//        String priority = "High";
//        task.setPriority(priority);
//        assertEquals(priority, task.getPriority());
//    }
//
//    @Test
//    public void testSetAndGetDueDate() {
//        LocalDate dueDate = LocalDate.now();
//        task.setDue_Date(dueDate);
//        assertEquals(dueDate, task.getDue_Date());
//    }
//
//    @Test
//    public void testSetAndGetCompleted() {
//        task.setCompleted(true);
//        assertTrue(task.isCompleted());
//    }
//
//    @Test
//    public void testSetAndGetCategory() {
//        task.setCategory(category);
//        assertEquals(category, task.getCategory());
//    }
//
//    @Test
//    public void testConstructorWithDescriptionAndPriorityAndDueDate() {
//        String description = "Test Task";
//        String priority = "High";
//        LocalDate dueDate = LocalDate.now();
//        Task newTask = new Task(description, priority, dueDate, false);
//        assertEquals(description, newTask.getDescription());
//        assertEquals(priority, newTask.getPriority());
//        assertEquals(dueDate, newTask.getDue_Date());
//        assertFalse(newTask.isCompleted());
//    }
//
//    @Test
//    public void testConstructorWithDescriptionAndCategory() {
//        String description = "Test Task";
//        Task newTask = new Task(category, description);
//        assertEquals(category, newTask.getCategory());
//        assertEquals(description, newTask.getDescription());
//    }
//}
