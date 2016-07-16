package com.thoughtworks.bookish.controller;

import com.thoughtworks.bookish.model.Book;
import com.thoughtworks.bookish.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.thoughtworks.bookish.specification.BookSpecs.isAuthorizedBy;
import static com.thoughtworks.bookish.specification.BookSpecs.publishedNYearsAgo;
import static org.springframework.data.jpa.domain.Specifications.where;

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
                               @RequestParam(value = "size", defaultValue = "12") int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return bookRepository.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public Iterable<Book> search(@RequestParam(value = "author") String author, @RequestParam(value = "years", defaultValue = "60") Long years) {
        return bookRepository.findAll(
                where(publishedNYearsAgo(years)).
                        and(isAuthorizedBy(author)));
    }
}
