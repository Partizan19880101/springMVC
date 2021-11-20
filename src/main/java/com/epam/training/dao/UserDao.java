package com.epam.training.dao;

import com.epam.training.model.User;

/**
 * The interface User dao.
 */
public interface UserDao extends Dao<User> {

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    User getUser(long id);
}
