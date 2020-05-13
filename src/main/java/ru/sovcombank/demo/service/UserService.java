package ru.sovcombank.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sovcombank.demo.model.User;
import ru.sovcombank.demo.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
