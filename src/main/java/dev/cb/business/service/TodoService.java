package dev.cb.business.service;

import dev.cb.business.domain.Todo;
import dev.cb.repository.TodoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return (List<Todo>) todoRepository.findAll();
    }

    public List<Todo> findAllByCompleted(boolean completed) {
        return todoRepository.findAllByCompleted(completed);
    }

    public Optional<Todo> findById(UUID id) {
        return todoRepository.findById(id);
    }

    public Todo createOrUpdate(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteById(UUID id) {
        todoRepository.deleteById(id);
    }

    public Optional<Todo> updateValited(UUID id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.updateValidated();
            createOrUpdate(todo);
        }
        return optionalTodo;
    }
}
