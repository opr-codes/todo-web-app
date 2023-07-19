package com.practice.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	//private TodoService todoService;	//Free of static list, uses h2 database instead and uses TodoRepository instead of TodoService
	private TodoRepository todoRepository;
	
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		//this.todoService = todoService;
		this.todoRepository=  todoRepository;
	}

	// /list-todos
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {	
		String username = getLoggedInUsername(model);
	
		//List<Todo> todos = todoService.findByUserName(username);
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}
	
	//GET, POST req both are handled
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {		
		String username = getLoggedInUsername(model);		
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);	//these values are stored in model; next line
		model.put("todo", todo);				//Setting an attribute called todo to model so it binds to modelAttribute in todo.jsp
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST) 
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {	
		//To ensure todo bean is validated before binding happens 
		
		if(result.hasErrors()) {		//If result contains error, shows error message and return to todo.jsp page
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);		//setting username in todo bean and saving it
		todoRepository.save(todo);
		//setting all values in todo bean coz todoRepository doesn't have any method that will take all the attributes
		//save() method on todoRepository that will accept todo as the input
		
		//todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());		//Automatic binding
																					//Used to set all values in todoService before
		
		return "redirect:list-todos";		//Redirect to listTodos.jsp after adding a todo
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {	//@RequestParam to capture id to be deleted
		//Delete todo
		todoRepository.deleteById(id);
		//todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {	//@RequestParam to capture id to be deleted
		Todo todo= todoRepository.findById(id).get();
		//Todo todo= todoService.findById(id);
		model.addAttribute("todo", todo);		//todo should be matching the name given in todo.jsp
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST) 
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {	
		//To ensure todo bean is validated before binding happens 
		
		if(result.hasErrors()) {		//If result contains error, shows error message and return to todo.jsp page
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		//todoService.updateTodo(todo);		
		return "redirect:list-todos";		//Redirect to listTodos.jsp after adding a todo
	}
	
	private String getLoggedInUsername(ModelMap model) {
		//Instead of getting loggedin user from the modal, we want to get it from Spring Security
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
