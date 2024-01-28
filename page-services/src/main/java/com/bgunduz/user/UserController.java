package com.bgunduz.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/page-services/user")
public class UserController {

    @PostMapping
    public ResponseEntity<User> addUser(){
        return null;
    }
}
