package dev.cb.controller;

import dev.cb.business.domain.Todo;
import dev.cb.business.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getAllByCompleted(@RequestParam(value = "completed", required = false) Boolean completed) {
        if (completed == null) {
            return ResponseEntity.ok(todoService.findAll());
        } else {
            return ResponseEntity.ok(todoService.findAllByCompleted(completed));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable UUID id) {
        Optional<Todo> optionalTodo = todoService.findById(id);
        return optionalTodo.isPresent() ? ResponseEntity.ok(optionalTodo.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.createOrUpdate(todo));
    }

    @PutMapping()
    public ResponseEntity<Todo> update(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.createOrUpdate(todo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateValidated(@PathVariable UUID id) {
        Optional<Todo> optionalTodo = todoService.updateValited(id);
        return optionalTodo.isPresent() ? ResponseEntity.ok(optionalTodo.get()) : ResponseEntity.notFound().build();
    }

    // should return 204 if ok (and need not further info), with empty body
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
