package com.epam.training.model.impl;

import lombok.Data;
import com.epam.training.model.User;

/**
 * The type User.
 */
@Data
public class UserImpl implements User {

    private long id;
    private String name;
    private String email;

    /**
     * Instantiates a new User.
     *
     * @param name  the name
     * @param email the email
     */
    public UserImpl(String name, String email) {
        this.id = id++;
        this.name = name;
        this.email = email;
    }
}
