/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import utility.BCrypt;
import utility.Utils;

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
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private UUID id;



    @Column(name = "username", unique = true)
    @Getter
    @Setter
    private String username;

    @Column(name = "password")
    @Getter(value = AccessLevel.PRIVATE)
    private String password;

    @Column(name = "email", unique = true)
    @Getter
    @Setter
    private String email;

    @Column(name = "token")
    @Getter
    @Setter
    private String token; // used for forget password

    @Column(name = "number")
    @Getter
    @Setter
    private long number;

    @Column(name = "used")
    @Getter
    @Setter
    private boolean used; // used to check if the forget password is used or not

    @Column(name = "session_id")
    @Getter
    @Setter
    private String sessionId;

    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "date")
    @Getter
    @Setter
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date; // used check the forget password token expiry date

    public User() {
    }

    public User(String username, String password, String email, long number) {
        this.username = username;
        this.setPassword(password);
        this.email = email;
        this.number = number;
    }

    final public void setSessionToken(String sessionToken) {
        this.sessionToken = Utils.hash(sessionToken);
    }

    /**
     * this method matches the given session token with the stored session token
     */
    final public boolean matchSessionToken(String token) {
        return Utils.hashEquals(this.sessionToken, Utils.hash(token));
    }

    /**
     * this method checks the given password matches with the stored password
     */
    final public boolean checkPassword(String passwordPlainText) {
        return BCrypt.checkpw(passwordPlainText, this.password);
    }

    /**
     * this method hashes the password and sets it
     */
    final public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

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
