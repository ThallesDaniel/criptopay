package com.ThallesDaniel.criptopay.controller;

import com.ThallesDaniel.criptopay.dto.UserRequest;
import com.ThallesDaniel.criptopay.entity.User;
import com.ThallesDaniel.criptopay.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest) throws MessagingException, UnsupportedEncodingException {
        User user = userRequest.toModel();

        User userSaved = userService.registerUser(user);
        return ResponseEntity.ok().body(userSaved);
    }

}
