package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.UserDto;
import com.fixyourhabitsbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String username) {
        UserDto getUser = userService.getUserByUsername(username);
        return ResponseEntity.ok().body(getUser);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {;
        String newUsername = userService.createUser(dto);
        userService.addAuthority(newUsername, "ROLE_USER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/admin")
    public ResponseEntity<UserDto> createAdminUser(@RequestBody UserDto dto) {;

        String newUsername = userService.createAdmin(dto);
        userService.addAuthority(newUsername, "ROLE_ADMIN");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto dto) {
        userService.updateUser(username, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}