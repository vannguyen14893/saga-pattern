package com.example.authorzation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String phone;
    private String email;
    private String password;
    private String secret;
    private int countLoginAttempt;
    private LocalDateTime lastFailedLoginDate;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private String language = "vi";
    private Integer otp;
    private LocalDateTime otpExpireDate;
    private String multifactorAuthenticationType;
    private String avatar;

//    public void setAccountNonLocked(boolean accountNonLocked) {
//        isAccountNonLocked = (!accountNonLocked && !getLastFailedLoginDate().plusSeconds(900).isBefore(LocalDateTime.now()));
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return phone;
    }

    public User(String email, String password, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, boolean isAccountNonExpired) {
        this.email = email;
        this.password = password;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = isAccountNonExpired;
    }

}
