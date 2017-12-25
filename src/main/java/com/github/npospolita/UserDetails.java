package com.github.npospolita;

import com.github.npospolita.model.User;
import org.springframework.stereotype.Component;

/**
 * INFO
 *
 * @author NPospolita
 * @since 06.12.2017
 */
@Component
public class UserDetails {

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
