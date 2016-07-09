package com.thoughtworks.bookish.controller;

import com.thoughtworks.bookish.model.Book;
import com.thoughtworks.bookish.model.User;
import com.thoughtworks.bookish.repository.BookRepository;
import com.thoughtworks.bookish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Autowired
    public FavoriteController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Book favorite(@RequestParam("email") String email, @RequestParam("bookId") Long bookId) {
        User user = userRepository.findByEmail(email);
        Book one = bookRepository.findOne(bookId);

        Set<Book> books = user.getBooks();
        books.add(one);
        userRepository.save(user);

        return one;
    }

    @RequestMapping
    public Iterable<Book> fetchAll(@RequestParam("email") String email) {
        return bookRepository.findByUserEmail(email);
    }
}
