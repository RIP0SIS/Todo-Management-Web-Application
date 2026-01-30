package com.first.springboot.firstwebapp.todo;

import com.first.springboot.firstwebapp.todo.exception.NotFoundException;
import com.first.springboot.firstwebapp.todo.jpa.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
//@SessionAttributes("Name")
public class RestControllerJpa {

    //REST API

    private final TodoRepository todoRepository;

    public RestControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private String getLoggedInUserName() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

/*
    Don’t expose usernames in URLs unless required

    @GetMapping("/users/{username}/todos")
    public List<Todo> getTodos(@PathVariable String username, Authentication authentication) {

        if (!username.equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return todoRepository.findByUsername(username);
    }


    @GetMapping("/users/{username}/todos")
    public List<Todo> getTodos(Authentication authentication) {
        return todoRepository.findByUsername(authentication.getName());
    }

*/

    @GetMapping("users/todos")
    public List<Todo> getAllTodosForLoggedInUser() {
        String username = getLoggedInUserName();
        List<Todo> todos = todoRepository.findByUsername(username);

        if (todos.isEmpty()) {
            throw new NotFoundException("No todos found for user: " + username);
        }

        return todos;
    }

    @GetMapping("users/todos/{id}")
    public Todo getTodo(@PathVariable int id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found with id: " + id));
    }

    @DeleteMapping("users/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {

        if (!todoRepository.existsById(id)) {
            throw new NotFoundException("Todo not found with id: " + id);
        }

        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); //204 - no content
    }

    @PutMapping("users/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo todo) {

        todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found with id: " + id));

        //Prevents client from changing the ID
        todo.setId(id);

        //Prevents client from changing the username
        todo.setUsername(getLoggedInUserName());

        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("users/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        //Prevents client from changing the username
        todo.setUsername(getLoggedInUserName());

        todoRepository.save(todo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
