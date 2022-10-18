package com.ericsson.hazelcastdemo.service;

import com.ericsson.hazelcastdemo.model.dto.Book;
import com.ericsson.hazelcastdemo.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable("books")
    public Optional<Book> findBookById(Long id){
        sleepFor5Seconds();
        return bookRepository.findById(id);
    }

    private void sleepFor5Seconds() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    @CacheEvict(value = "books", allEntries = true)
    public void evictCache() {
    }
}
