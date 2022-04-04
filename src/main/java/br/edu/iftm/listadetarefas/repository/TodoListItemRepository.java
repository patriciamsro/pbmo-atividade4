package br.edu.iftm.listadetarefas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.listadetarefas.domain.TodoList;
import br.edu.iftm.listadetarefas.domain.TodoListItem;

@Repository
public interface TodoListItemRepository extends CrudRepository<TodoListItem,Integer> {
   
    List<TodoListItem> findByTodoList(TodoList todoList);
}
