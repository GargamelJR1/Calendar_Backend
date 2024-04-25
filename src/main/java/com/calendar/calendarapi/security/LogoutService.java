package com.calendar.calendarapi.security;

import com.calendar.calendarapi.security.token.Token;
import com.calendar.calendarapi.security.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler
{
    private final TokenRepository tokenRepository;

    public LogoutService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String token;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return;
        }

        token = authHeader.substring(7);
        Token tokenDb = tokenRepository.findByToken(token).orElse(null);
        if (tokenDb != null) {
            tokenDb.setLogged(false);
            tokenRepository.delete(tokenDb);
            SecurityContextHolder.clearContext();
        }
    }
}
