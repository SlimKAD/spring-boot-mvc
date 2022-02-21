package com.slim.springbootrestfulservice.user;

import com.slim.springbootrestfulservice.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("jpa/users")
    public List<User> retrieveAllUsers () {

        return userRepository.findAll();
    };

    @GetMapping("jpa/users/{id}")
    public User getUser(@PathVariable Integer id) {

        Optional<User> user =  userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("id="+ id);
        };
        return user.get();
    };

    @PostMapping("jpa/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {

        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    };

    @DeleteMapping("jpa/users/{id}")
    public void deleteUser(@PathVariable  Integer id) {
        userRepository.deleteById(id);

    };

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> retrievePostsById (@PathVariable int id) {
        Optional<User> user =  userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id="+ id);
        }
        return user.get().getPosts();
    };


}
