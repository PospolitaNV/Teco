package com.github.npospolita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User entity
 *
 * @author NPospolita
 * @since 03.12.2017
 */
@Entity(name = "user_table")
public class User {

    @Id
    String login;

    @Column(nullable = false)
    String password;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
