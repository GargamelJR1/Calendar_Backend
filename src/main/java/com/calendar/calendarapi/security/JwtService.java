package com.calendar.calendarapi.security;

import com.calendar.calendarapi.security.token.Token;
import com.calendar.calendarapi.security.token.TokenRepository;
import com.calendar.calendarapi.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService
{
    private final String SECRET_KEY = "644e043ad0c6aadaad542ee167fba8f4c010310adcc96c000b189e94a118b057";
    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public boolean isTokenValid(String token, String requestUsername) {
        String username = extractUsername(token);

        boolean valid = tokenRepository.findByToken(token)
                .map(Token::isLogged)
                .orElse(false);

        return valid && isTokenNotExpired(token) && username != null && username.equals(requestUsername);
    }

    private boolean isTokenNotExpired(String token) {
        return extractExpirationDate(token).after(new Date());
    }

    public Date extractExpirationDate(String token) {
        return extractClaims(token).getExpiration();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey())
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode((SECRET_KEY)));
    }
}
