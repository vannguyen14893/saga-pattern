package org.demo.cucumber.service;

import lombok.RequiredArgsConstructor;
import org.demo.cucumber.entity.User;
import org.demo.cucumber.reppository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll(Pageable.ofSize(10)).stream().toList();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User request) {
        User user = findByEmail(request.getEmail());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User deleteByEmail(String email) {
        User user = findByEmail(email);
        userRepository.delete(findByEmail(email));
        return user;
    }
}
