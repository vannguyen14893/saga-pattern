package com.example.authorzation;

import com.example.authorzation.repository.UserRepository;
import com.example.authorzation.service.CustomUserDetailCache;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor

public class IamApplication {
    private final CustomUserDetailCache customUserDetailCache;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }

    @PostConstruct
    public void addUser() {
//        User user = new User();
//        user.setEmail("ducvan14893@gmail.com");
//        user.setPassword(passwordEncoder.encode("123456a@"));
//        user.setPhone("1234567890");
//        user.setFullName("ndvan");
//        user.setAccountNonExpired(true);
//        user.setCredentialsNonExpired(true);
//        user.setAccountNonLocked(true);
//        user.setEnabled(true);
        //userRepository.save(user);
        customUserDetailCache.putUserInCache(userRepository.findByPhone("1234567890"));
    }
}
