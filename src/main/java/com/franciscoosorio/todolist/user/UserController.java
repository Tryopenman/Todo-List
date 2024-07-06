package com.franciscoosorio.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity registerUser(@RequestBody UserModel user){

        var userExists = userRepository.findByUsername(user.getUsername());

        if (userExists != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        }

        var passwordHashred = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordHashred);

        var userRegistered = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegistered);
    }
    
}
