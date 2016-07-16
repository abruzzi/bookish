package com.thoughtworks.bookish.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.thoughtworks.bookish.BookishApplication;
import com.thoughtworks.bookish.model.Book;
import com.thoughtworks.bookish.repository.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest("server.port:0")
@SpringApplicationConfiguration(BookishApplication.class)
@ActiveProfiles(value = "integration")
public class BookControllerTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private BookRepository bookRepository;

    @After
    public void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    public void should_create_a_new_book() {
        given().
                contentType(ContentType.JSON).
                body(prepareBook()).
        when().
                post("/books").
        then().
                statusCode(201).
                body("id", notNullValue()).
                body("title", is("代码整洁之道")).
                body("author", is("Robert C. Martin, 韩磊")).
                body("asin", is("B0031M9GHC")).
                body("description", is("代码整洁之道"));
    }

    @Test
    public void should_return_list_of_books() {
        Iterable<Book> saved = bookRepository.save(prepareBooks());

        given().
        when().
                get("/books").
        then().
                statusCode(200).
                body("content.title", hasItems("代码整洁之道", "重构:改善既有代码的设计", "敏捷软件开发(原则模式与实践)"));
    }

    @Test
    public void should_return_some_books_based_on_search_by_title() {
        Iterable<Book> saved = bookRepository.save(prepareBooks());

        given().
        when().
                get("/books?title=代码").
        then().
                statusCode(200).
                body("content.title", hasItems("代码整洁之道", "重构:改善既有代码的设计")).
                body("content.title", not(hasItem("敏捷软件开发(原则模式与实践)")));
    }

    @Test
    public void should_return_list_of_books_with_pagination() {
        Iterable<Book> saved = bookRepository.save(prepareBooks());

        given().
        when().
                get("/books?page=0&size=2").
        then().
                statusCode(200).
                body("content.title", hasItems("代码整洁之道", "重构:改善既有代码的设计")).
                body("content.title", not(hasItem("敏捷软件开发(原则模式与实践)")));
    }

    @Test
    public void should_return_a_book() {
        Book saved = bookRepository.save(prepareBook());

        given().
        when().
                get("/books/"+saved.getId()).
        then().
                statusCode(200).
                body("title", is("代码整洁之道"));
    }

    private Iterable<Book> prepareBooks() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("代码整洁之道", "Robert C. Martin, 韩磊", "9787115216878", "B0031M9GHC"));
        books.add(new Book("重构:改善既有代码的设计", "Martin Fowler, 熊节", "9787115369093", "B011LPUB42"));
        books.add(new Book("敏捷软件开发(原则模式与实践)", "Robert C. Martin, 邓辉", "9787302071976", "B00116MMA8"));

        return books;
    }

    private Book prepareBook() {
        Book book = new Book("代码整洁之道", "Robert C. Martin, 韩磊", "9787115216878", "B0031M9GHC");
        book.setDescription("代码整洁之道");
        return book;
    }

}
