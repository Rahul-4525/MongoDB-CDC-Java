package com.microservices.AccountService.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.AccountService.entity.User;
import com.microservices.AccountService.service.UserService;
import org.bson.BsonDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@RestController
public class APIController {


    @Autowired
    private UserService userService;

    @PostMapping("/save/users")
    public ResponseEntity<Void> saveUserData() throws IOException {
        this.userService.saveUsers();
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @PutMapping("/modify/user/{userId}/{name}")
    public ResponseEntity<BsonDocument> updateUser(@PathVariable String userId,
                                                   @PathVariable String name)
            throws InterruptedException, ExecutionException {

        this.userService.updateUser(userId, name);

        Optional<BsonDocument> updated = this.userService.waitForKeyAsync(userId, 5000).get();

        return updated.isPresent() ? ResponseEntity.accepted().body(updated.get()) :
                ResponseEntity.internalServerError().build();
    }



}
