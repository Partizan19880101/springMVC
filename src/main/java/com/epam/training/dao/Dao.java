package com.epam.training.dao;

import java.util.List;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface Dao<T> {

    /**
     * Gets all.
     *
     * @return the all
     */
    List<T> getAll();

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delete(long id);

    /**
     * Create t.
     *
     * @param t the t
     * @return the t
     */
    T create(T t);
}
