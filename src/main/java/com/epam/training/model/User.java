package com.epam.training.model;

/**
 * The type User.
 */
public interface User {

    /**
     * User Id. UNIQUE.
     *
     * @return User Id.
     */
    long getId();

    /**
     * Sets id.
     *
     * @param id the id
     */
    void setId(long id);

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();

    /**
     * Sets name.
     *
     * @param name the name
     */
    void setName(String name);

    /**
     * User email. UNIQUE.
     *
     * @return User email.
     */
    String getEmail();

    /**
     * Sets email.
     *
     * @param email the email
     */
    void setEmail(String email);
}
