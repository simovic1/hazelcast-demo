package com.ericsson.hazelcastdemo.controller;

import com.ericsson.hazelcastdemo.model.dto.Book;
import com.ericsson.hazelcastdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        var book = bookService.findBookById(id);

        return book.isPresent() ?
                ResponseEntity.ok(book.get()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void evictCache(){
        bookService.evictCache();
    }
}
