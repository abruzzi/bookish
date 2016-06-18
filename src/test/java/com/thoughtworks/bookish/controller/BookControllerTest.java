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

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
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
                body("author", is("Robert C. Martin, 韩磊")).body("asin", is("B0031M9GHC"));
    }

    @Test
    public void should_return_list_of_books() {
        bookRepository.save(prepareBook());
        given().
        when().
                get("/books").
        then().
                statusCode(200).
                body("title", hasItems("代码整洁之道"));
    }

    @Test
    public void should_return_a_book() {
        Book save = bookRepository.save(prepareBook());

        given().
        when().
                get("/books/"+save.getId()).
        then().
                statusCode(200).
                body("title", is("代码整洁之道"));
    }

    private Book prepareBook() {
        return new Book("代码整洁之道", "Robert C. Martin, 韩磊", "9787115216878", "B0031M9GHC");
    }

}
