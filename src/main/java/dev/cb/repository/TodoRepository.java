package dev.cb.repository;

import dev.cb.business.domain.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends CrudRepository<Todo, UUID>, PagingAndSortingRepository<Todo, UUID> {
    List<Todo> findAllByCompleted(boolean completed);
}
