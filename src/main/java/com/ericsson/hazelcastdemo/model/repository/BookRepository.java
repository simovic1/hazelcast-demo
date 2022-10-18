package com.ericsson.hazelcastdemo.model.repository;

import com.ericsson.hazelcastdemo.model.dto.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
