package com.first.springboot.firstwebapp.todo;

import com.first.springboot.firstwebapp.todo.jpa.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
//@SessionAttributes("Name")
public class MvcControllerJpa {

    //Creating View for UI

    private final TodoRepository todoRepository;

    public MvcControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private String getLoggedInUserName() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "list-todos")
    public String listAllTodos(ModelMap model) {
        String userName = getLoggedInUserName();
        List<Todo> todos = todoRepository.findByUsername(userName);
        model.addAttribute("todos", todos);
        return "ListTodos";
    }

    //2 Way binding = Bean to Form to Bean = flexibility
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPages(ModelMap model) {
        String username = getLoggedInUserName();
        //One side binding
        //Creating a todo and mapping to form attributes of todo jsp to render on browser
        Todo todo = new Todo(0, username, "", LocalDate.now().plusMonths(1), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        //Two side binding
        //Mapping todo jsp form data (user sent description) to Todo todo object (description field of obj)
        String username = getLoggedInUserName();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).orElse(null);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updatedTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        String username = getLoggedInUserName();
        todo.setUsername(username);  //Setting the username of the todo object
        todoRepository.save(todo);
        return "redirect:/list-todos";
    }

}
