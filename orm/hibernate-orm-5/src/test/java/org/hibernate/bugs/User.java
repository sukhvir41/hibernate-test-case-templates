/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.bugs;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author sukhvir
 */
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "username"})})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "UUID")
    private UUID id;



    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "token")
    private String token; // used for forget password

    @Column(name = "number")
    private long number;

    @Column(name = "used")
    private boolean used; // used to check if the forget password is used or not

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "session_token")
    private String sessionToken;

    //@Column(name = "date")
    //@Convert(converter = LocalDateTimeConverter.class)
    //private LocalDateTime date; // used check the forget password token expiry date

    public User() {
    }

    public User(String username, String password, String email, long number) {
        this.username = username;
       // this.setPassword(password);
        this.email = email;
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    /* final public void setSessionToken(String sessionToken) {
        this.sessionToken = Utils.hash(sessionToken);
    }*/

    /**
     * this method matches the given session token with the stored session token
     */
    /*final public boolean matchSessionToken(String token) {
        return Utils.hashEquals(this.sessionToken, Utils.hash(token));
    }*/

    /**
     * this method checks the given password matches with the stored password
     */
   /* final public boolean checkPassword(String passwordPlainText) {
        return BCrypt.checkpw(passwordPlainText, this.password);
    }*/

    /**
     * this method hashes the password and sets it
     */
    /*final public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(10));
    }*/

    public abstract UserType getUserType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                username.equals(user.username) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }
}
