package br.edu.iftm.listadetarefas.controller;

import br.edu.iftm.listadetarefas.domain.TodoListItem;
import br.edu.iftm.listadetarefas.service.TodoListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todolist/{idList}/todolistitem")
class TodoListItemController {

    @Autowired
    TodoListItemService service;

    @GetMapping
    public ResponseEntity<List<TodoListItem>> getAll(@PathVariable("idList") Integer id) {
        List<TodoListItem> items = service.GetAll(id);
        if (items.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoListItem> getById(@PathVariable("id") Integer id) {
        Optional<TodoListItem> existingItemOptional = service.GetById(id);

        if (existingItemOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(existingItemOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<TodoListItem> create(@RequestBody TodoListItem todoListItem, 
                @PathVariable("idList") Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(todoListItem, id));
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoListItem> update(@PathVariable("id") Integer id,
            @RequestBody TodoListItem item, @PathVariable("idList") Integer idList) {
        TodoListItem todoListItem = service.update(item, id, idList);
        if (todoListItem != null) {
            return ResponseEntity.status(HttpStatus.OK).body(todoListItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado", exc);
        }
    }
}