package com.calendar.calendarapi.security.authentication;

import com.calendar.calendarapi.security.JwtService;
import com.calendar.calendarapi.security.token.Token;
import com.calendar.calendarapi.security.token.TokenRepository;
import com.calendar.calendarapi.user.User;
import com.calendar.calendarapi.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, TokenRepository tokenRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return authenticate(user);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        String tokenString = jwtService.generateToken(user);
        saveUserToken(user, tokenString);

        return new AuthenticationResponse(tokenString);
    }

    public AuthenticationResponse authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        User userFromDb = userRepository.findByEmail(user.getEmail()).orElseThrow();
        String token = jwtService.generateToken(userFromDb);

        revokeUserTokens(userFromDb);
        saveUserToken(userFromDb, token);

        return new AuthenticationResponse(token);
    }

    private void saveUserToken(User user, String token) {
        Token userToken = new Token();
        userToken.setToken(token);
        userToken.setUser(user);
        userToken.setLogged(true);
        tokenRepository.save(userToken);
    }

    private void revokeUserTokens(User user) {
        List<Token> userTokens = tokenRepository.findValidTokensByUser(user.getId());

        if (userTokens != null && !userTokens.isEmpty()) {
            userTokens.forEach(token -> {
                token.setLogged(false);
                tokenRepository.save(token);
            });
        }
    }
}
