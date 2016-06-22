package com.thoughtworks.bookish.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.thoughtworks.bookish.BookishApplication;
import com.thoughtworks.bookish.model.User;
import com.thoughtworks.bookish.repository.UserRepository;
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
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BookishApplication.class)
@WebIntegrationTest("server.port:0")
@ActiveProfiles("integration")
public class UserControllerTest {
    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    UserRepository userRepository;

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void should_create_user() {
        given().contentType(ContentType.JSON).body(prepareUser()).
                when().post("/users").
                then().statusCode(201).
                body("id", notNullValue()).
                body("name", is("Juntao Qiu")).
                body("email", is("juntao.qiu@gmail.com"));
    }

    private User prepareUser() {
        User user = new User();
        user.setName("Juntao Qiu");
        user.setEmail("juntao.qiu@gmail.com");
        user.setPassword("password");
        return user;
    }
}
