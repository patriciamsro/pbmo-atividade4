package br.edu.iftm.listadetarefas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.listadetarefas.domain.TodoList;
import br.edu.iftm.listadetarefas.domain.TodoListItem;
import br.edu.iftm.listadetarefas.repository.TodoListItemRepository;
import br.edu.iftm.listadetarefas.repository.TodoListRepository;

@Service
public class TodoListItemService {

    @Autowired
    private TodoListItemRepository repository;
    @Autowired
    private TodoListRepository listRepository;

    public List<TodoListItem> GetAll(Integer iDList) {
        TodoList todoList = listRepository.findById(iDList).get();
        return repository.findByTodoList(todoList);
    }

    public Optional<TodoListItem> GetById(Integer id) {
        return repository.findById(id);
    }

    public TodoListItem create(TodoListItem todoListItem, Integer iDList) {
        TodoList todoList = listRepository.findById(iDList).get();
        todoListItem.setTodoList(todoList);
        return repository.save(todoListItem);
    }

    public TodoListItem update(TodoListItem todoListItem, Integer id, Integer idList) {
        if (repository.existsById(id)) {
            todoListItem.setId(id);
            todoListItem.setTodoList(listRepository.findById(idList).get());
            return repository.save(todoListItem);
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
