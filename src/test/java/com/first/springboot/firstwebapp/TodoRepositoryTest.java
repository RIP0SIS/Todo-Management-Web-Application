package com.first.springboot.firstwebapp;

import com.first.springboot.firstwebapp.todo.Todo;
import com.first.springboot.firstwebapp.todo.jpa.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void saveTodo_shouldPersistTodo() {
        Todo todo = new Todo(
                0,
                "fenrir",
                "Learn JUnit",
                LocalDate.now().plusDays(10),
                false
        );

        Todo savedTodo = todoRepository.save(todo);

        assertNotNull(savedTodo.getId());
        assertEquals("fenrir", savedTodo.getUsername());
    }



    @Test
    void findByUsername_shouldReturnUserTodos() {

        todoRepository.save(
                new Todo(0, "fenrir", "learn aws task 1", LocalDate.now().plusDays(5), false)
        );
        todoRepository.save(
                new Todo(0, "fenrir", "learn junit task 2", LocalDate.now().plusDays(13), true)
        );
        todoRepository.save(
                new Todo(0, "other", "learn junit task X", LocalDate.now().plusDays(8), false)
        );

        List<Todo> todos = todoRepository.findByUsername("fenrir");

        assertEquals(2, todos.size());
    }


    @Test
    void findById_shouldReturnTodoIfExists() {

        Todo saved = todoRepository.save(
                new Todo(0, "fenrir", "Find me if u can", LocalDate.now().plusDays(10), false)
        );

        Optional<Todo> result = todoRepository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Find me if u can", result.get().getDescription());
    }


    @Test
    void deleteById_shouldRemoveTodo() {

        Todo saved = todoRepository.save(
                new Todo(0, "fenrir", "Learn Aws task 1", LocalDate.now().plusDays(3), false)
        );

        todoRepository.deleteById(saved.getId());

        Optional<Todo> deleted = todoRepository.findById(saved.getId());

        assertTrue(deleted.isEmpty());
    }

}
