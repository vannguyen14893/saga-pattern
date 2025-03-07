package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseToken {
    public static Long getUserIdFromJWT(String token) {
        Claims claims =
                Jwts.parser().setSigningKey("SecretKeyToGenJWTs").parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
