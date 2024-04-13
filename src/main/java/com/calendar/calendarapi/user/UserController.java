package com.calendar.calendarapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUserById(@RequestParam long id) {
        Optional<UserDTO> user = userService.getUserById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        return userService.addUser(user)
                .map(userDto -> ResponseEntity.status(HttpStatus.CREATED).body(userDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
