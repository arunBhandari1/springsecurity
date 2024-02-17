package com.secuirty.springboot.auth;

import com.secuirty.springboot.config.JwtService;
import com.secuirty.springboot.user.Role;
import com.secuirty.springboot.user.User;
import com.secuirty.springboot.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private  PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticateReqest request) {
        provider.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        userRepository.findByEmail(request.getEmail()).orElseThrow();
        return null;
    }
    private AuthenticationProvider provider;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder().email(request.getEmail()).firstName(request.getFirstName())
                .lastName(request.getLastName()).password(encoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        
        return  AuthenticationResponse.builder().token(jwtToken).build();

    }

            //authenticate


}
