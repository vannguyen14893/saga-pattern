package com.example.authorzation;

import com.example.authorzation.repository.UserRepository;
import com.example.authorzation.service.CustomUserDetailCache;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class IamApplication {
    private final CustomUserDetailCache customUserDetailCache;
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }

    @PostConstruct
    public void addUser() {
        //log.info("add user {}", passwordEncoder.encode("secret"));
//        User user = new User();
//        user.setEmail("ducvan14894@gmail.com");
//        user.setPassword(passwordEncoder.encode("123456a@"));
//        user.setPhone("1234567891");
//        user.setFullName("ndvan");
//        user.setAccountNonExpired(true);
//        user.setCredentialsNonExpired(true);
//        user.setAccountNonLocked(true);
//        user.setEnabled(true);
//        userRepository.save(user);
        customUserDetailCache.putUserInCache(userRepository.findByPhone("1234567890"));
        customUserDetailCache.putUserInCache(userRepository.findByPhone("1234567891"));

    }
}
