package com.slim.springbootrestfulservice.post;

import com.slim.springbootrestfulservice.user.User;
import com.slim.springbootrestfulservice.user.UserNotFoundException;
import com.slim.springbootrestfulservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostJpaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> retrievePostsById (@PathVariable int id) {
        Optional<User> user =  userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id="+ id);
        }
        return user.get().getPosts();
    };

    @PostMapping("jpa/users/{id}/posts")
    public ResponseEntity<Object> addPost(@PathVariable int id, @Valid @RequestBody Post post) {

        Optional<User> user =  userRepository.findById(id);

        if(!user.isPresent()) {
            throw new UserNotFoundException("id="+ id);
        };

        post.setUser(user.get());
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();

    };
}
