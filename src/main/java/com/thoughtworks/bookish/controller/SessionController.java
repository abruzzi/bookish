package com.thoughtworks.bookish.controller;

import com.thoughtworks.bookish.model.AuthenticationToken;
import com.thoughtworks.bookish.model.User;
import com.thoughtworks.bookish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController {
    private UserRepository userRepository;

    @Autowired
    public SessionController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationToken login(@RequestBody User user) throws AuthenticationFailedException {
        User byEmail = userRepository.findByEmail(user.getEmail());

        if(byEmail == null) {
            throw new AuthenticationFailedException("authentication failed: "+user.getEmail());
        }

        return new AuthenticationToken(user.getName(), user.getEmail());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public AuthenticationToken logout(@RequestBody User user) throws AuthenticationFailedException {
        User byEmail = userRepository.findByEmail(user.getEmail());

        if(byEmail == null) {
            throw new AuthenticationFailedException("authentication failed: "+user.getEmail());
        }

        return new AuthenticationToken(user.getName(), user.getEmail());
    }

    private class AuthenticationFailedException extends RuntimeException {
        public AuthenticationFailedException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(value = SessionController.AuthenticationFailedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "authentication failed")
    public void authenticationFailed() {}
}
