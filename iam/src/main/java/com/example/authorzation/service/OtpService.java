package com.example.authorzation.service;

import com.example.authorzation.entity.User;
import com.example.authorzation.exceptions.BusinessExceptionHandler;
import com.example.authorzation.repository.UserRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Service class responsible for handling One-Time Password (OTP) operations.
 * This includes generation, validation, and management of OTP codes for user authentication.
 */
@RequiredArgsConstructor
@Service
public class OtpService {
    private final UserRepository userRepository;
    private final CustomUserDetailCache customUserDetailCache;

    /**
     * Creates a new OTP for the given user and stores it in the database.
     * The OTP is valid for 5 minutes from creation.
     *
     * @param userDetails the user entity for whom to create the OTP
     * @return the generated OTP as a string
     */
    public String createOtp(User userDetails) {
        String otp = String.valueOf(new Random().nextInt(999999));
        userDetails.setOtp(Integer.parseInt(otp));
        userDetails.setOtpExpireDate(LocalDateTime.now().plusMinutes(5));
        User save = userRepository.save(userDetails);
        customUserDetailCache.putUserInCache(save);
        return otp;
    }

    /**
     * Validates the provided OTP for the given username.
     *
     * @param otp      the OTP string to validate
     * @param username the username (phone number) associated with the OTP
     * @return the validated OTP as an integer
     * @throws NotFoundExceptionHandler if the user or OTP is not found
     * @throws BusinessExceptionHandler if the OTP is invalid or expired
     */
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
