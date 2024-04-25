package com.calendar.calendarapi.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long>
{
    @Query("""
            SELECT t FROM Token t JOIN t.user u
            WHERE u.id = :userId
            """)
    List<Token> findValidTokensByUser(long userId);

    Optional<Token> findByToken(String token);
}
