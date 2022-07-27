package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.UserDto;
import com.fixyourhabitsbackend.exceptions.BadRequestException;
import com.fixyourhabitsbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
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

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
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

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

}