package com.backend.urlink.controllers;

import com.backend.urlink.dto.CollectionDTO;
import com.backend.urlink.dto.UserDTO;
import com.backend.urlink.exceptions.RestException;
import com.backend.urlink.service.CollectionService;
import com.backend.urlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    private final CollectionService collectionService;

    @Autowired
    public UserController(UserService userService, CollectionService collectionService) {
        this.userService = userService;
        this.collectionService = collectionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId) throws RestException {
        var user = userService.getUserByIdService(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/collection/{userid}")
    public ResponseEntity<List<CollectionDTO>> getAllCollectionByUser(@PathVariable("userid") Long userId) {
        var result = collectionService.getAllCollectionByUserService(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) throws RestException {
        var savedUserDTO = userService.saveUserService(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO user) throws RestException {
        var updateUserDTO = userService.updateUserByIdService(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updateUserDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) throws RestException {
        userService.deleteUserService(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
