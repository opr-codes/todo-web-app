package com.practice.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "udemy", "Get AWS Certified", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "udemy", "Learn DevOps", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "udemy", "Learn Full Stack Development", LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUserName(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
		//On todo, if username matches, then its matching
		//Converting it into a stream. If there're 10 todos, each of them is processed individually and condn is checked
		//If condn matched, then todo is returned back otherwise not
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) { // To add a todo
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		//todo.getId() == id;
		//todo -> todo.getId() == id;	//to check if todo has the matching id
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;		//predicate- condn when we want to remove the todo
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;	//predicate helps to match id against the list of todos
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
		
	}
	
	
	
	
}
