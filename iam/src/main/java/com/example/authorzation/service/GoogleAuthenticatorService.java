package com.example.authorzation.service;

import com.example.authorzation.entity.User;
import com.example.authorzation.exceptions.BusinessExceptionHandler;
import com.example.authorzation.repository.UserRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleAuthenticatorService {

    private static final String ISSUER = "IAM-APP";
    private final UserRepository userRepository;
    private final CustomUserDetailCache customUserDetailCache;

    // Generate a new TOTP key
    public String generateKey() {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        return key.getKey();
    }

    // Validate the TOTP code
    public Integer isValid(String secret, String code) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator(
                new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder().build());
        int totp = Integer.parseInt(code);
        boolean authorize = gAuth.authorize(secret, totp);
        if (!authorize) {
            throw new BusinessExceptionHandler("otp_invalid");
        }
        return totp;
    }

    // Generate a QR code URL for Google Authenticator
    public String generateQRUrl(User user) {
        if (user.getSecret() == null) {
            String secret = generateKey();
            user.setSecret(secret);
            userRepository.save(user);
            customUserDetailCache.putUserInCache(user);
            String url = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(
                    ISSUER,
                    user.getPhone(),
                    new GoogleAuthenticatorKey.Builder(secret).build());
            return generateQRBase64(url);
        }
        return "0";
    }

    // Generate a QR code image in Base64 format
    public static String generateQRBase64(String qrCodeText) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 300, 300, hintMap);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (WriterException | IOException e) {
            return null;
        }
    }
}
