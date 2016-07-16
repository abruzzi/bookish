package com.thoughtworks.bookish.controller;

import com.thoughtworks.bookish.model.Book;
import com.thoughtworks.bookish.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.thoughtworks.bookish.specification.BookSpecs.hasTitleLike;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Book> list(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "12") int size,
                               @RequestParam(value = "title", defaultValue = "") String title) {
        PageRequest pageRequest = new PageRequest(page, size);
        return bookRepository.findAll(hasTitleLike(title), pageRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findOne(id);
    }

}
