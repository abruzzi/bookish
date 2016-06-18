package com.thoughtworks.bookish.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.thoughtworks.bookish.BookishApplication;
import com.thoughtworks.bookish.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest("server.port:0")
@SpringApplicationConfiguration(BookishApplication.class)
public class BookControllerTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
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
                body("title", is("Refactoring")).
                body("author", is("Martin Fowler"));
    }

    private Book prepareBook() {
        return new Book("Refactoring", "Martin Fowler");
    }

}
