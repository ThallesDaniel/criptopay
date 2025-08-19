package com.ThallesDaniel.criptopay.service;

import com.ThallesDaniel.criptopay.dto.UserResponse;
import com.ThallesDaniel.criptopay.entity.User;
import com.ThallesDaniel.criptopay.repository.UserRepository;
import com.ThallesDaniel.criptopay.util.RandomString;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    //UserResponse
    public User registerUser(User user) throws MessagingException, UnsupportedEncodingException {
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists");
        }else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            User savedUser = userRepository.save(user);

            return savedUser;

//            UserResponse userResponse = new UserResponse(
//                    savedUser.getId(),
//                    savedUser.getName(),
//                    savedUser.getEmail(),
//                    savedUser.getPassword());
//
//            mailService.sendVerificationEmail(user);
//            return userResponse;
        }
    };

}
