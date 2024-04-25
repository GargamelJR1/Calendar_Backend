package com.calendar.calendarapi.security.token;

import com.calendar.calendarapi.user.User;
import jakarta.persistence.*;

@Entity
public class Token
{
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String token;

    private boolean isLogged;

    @ManyToOne
    private User user;

    public Token() {
    }

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
        this.isLogged = false;
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
