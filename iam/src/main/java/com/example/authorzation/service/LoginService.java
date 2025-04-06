package com.example.authorzation.service;

import com.example.authorzation.entity.User;
import com.example.authorzation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final CustomUserDetailCache customUserDetailCache;

    public void loginFail(String username) {
        User user = userRepository.findByPhone(username);
        int countLoginAttempt = user.getCountLoginAttempt() + 1;
        user.setCountLoginAttempt(countLoginAttempt);
        user.setLastFailedLoginDate(LocalDateTime.now());
        if (user.getCountLoginAttempt() > 2) {
            user.setAccountNonLocked(false);
        }
        User save = userRepository.save(user);
        customUserDetailCache.putUserInCache(save);
    }

    public void loginSuccess(String username) {
        User user = userRepository.findByPhone(username);
        user.setCountLoginAttempt(0);
        user.setLastFailedLoginDate(LocalDateTime.now());
        user.setAccountNonLocked(true);
        userRepository.save(user);
        customUserDetailCache.putUserInCache(user);
    }
}
