package com.example.authorzation.service;

import com.example.authorzation.entity.User;
import com.example.authorzation.exceptions.BusinessExceptionHandler;
import com.example.authorzation.repository.UserRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class OtpService {
    private final UserRepository userRepository;
    private final CustomUserDetailCache customUserDetailCache;

    public String createOtp(User userDetails) {
        String otp = String.valueOf(new Random().nextInt(999999));
        userDetails.setOtp(Integer.parseInt(otp));
        userDetails.setOtpExpireDate(LocalDateTime.now().plusMinutes(5));
        User save = userRepository.save(userDetails);
        customUserDetailCache.putUserInCache(save);
        return otp;
    }

    public Integer validateOtp(String otp, String username) {
        Integer otpNumber = Integer.parseInt(otp);
        User user = userRepository.findByPhoneAndOtp(username, otpNumber);
        if (user == null) throw new NotFoundExceptionHandler("Mã otp");
        if (!user.getOtp().equals(otpNumber)) {
            throw new BusinessExceptionHandler("otp_invalid");
        }
        if (user.getOtpExpireDate() != null && LocalDateTime.now().isAfter(user.getOtpExpireDate())) {
            throw new BusinessExceptionHandler("otp_expired");
        }
        user.setOtp(null);
        user.setOtpExpireDate(null);
        User save = userRepository.save(user);
        customUserDetailCache.putUserInCache(save);
        return Integer.parseInt(otp);
    }
}
