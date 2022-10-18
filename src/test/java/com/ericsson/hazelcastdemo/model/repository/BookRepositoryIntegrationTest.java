package com.ericsson.hazelcastdemo.model.repository;

import com.ericsson.hazelcastdemo.HazelcastDemoApplication;
import com.ericsson.hazelcastdemo.model.dto.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HazelcastDemoApplication.class)
public class BookRepositoryIntegrationTest {
    @Autowired
    CacheManager cacheManager;

    @Autowired
    BookRepository repository;

    @BeforeEach
    void setUp(){
        repository.save(new Book(1l, "Book 1"));
        repository.save(new Book(2l, "Book 2"));
    }

    private Optional<Book> getCachedBook(Long id) {
        return ofNullable(cacheManager.getCache("books")).map(b -> b.get(id, Book.class));
    }

    @Test
    void givenBookThatShouldBeCached_whenFindById_thenResultShouldBePutInCache() {
        var book1 = repository.findById(1l);
        var book2 = repository.findById(2l);
        assertEquals(book1, getCachedBook(1l));
        assertEquals(book2, getCachedBook(2l));
    }
}
