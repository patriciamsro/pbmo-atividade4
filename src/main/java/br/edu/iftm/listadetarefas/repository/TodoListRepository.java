package br.edu.iftm.listadetarefas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.listadetarefas.domain.TodoList;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList,Integer> {

    @Override
    public List<TodoList> findAll();
}
