package com.calendar.calendarapi.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDTO> getUserById(long id) {
        return userRepository.findById(id).map(UserDTO::new);
    }

    public Optional<UserDTO> addUser(User user) {
        return Optional.of(userRepository.save(user)).map(UserDTO::new);
    }
}
