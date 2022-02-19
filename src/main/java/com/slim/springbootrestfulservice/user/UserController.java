package com.slim.springbootrestfulservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers () {
        return userDaoService.getAllUsers();
    };

    @GetMapping("/users/{id}")
   public User getUser(@PathVariable  Integer id) {

        User user =  userDaoService.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id="+ id);
        };
        return user;
   };

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {

         User savedUser = userDaoService.addUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

         return ResponseEntity.created(location).build();

    };

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable  Integer id) {

        User user =  userDaoService.deleteOne(id);
        if(user == null) {
            throw new UserNotFoundException("id="+ id);
        };
    };



}
