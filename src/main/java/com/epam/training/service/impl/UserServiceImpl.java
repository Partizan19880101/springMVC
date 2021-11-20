package com.epam.training.service.impl;

import com.epam.training.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.epam.training.model.User;
import com.epam.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserDao userDao;

    /**
     * Gets user by its id.
     *
     * @param userId
     * @return User.
     */
    @Override
    public User getUserById(long userId) {
        log.info("Attempting to get user with id {}", userId);
        return userDao.getUser(userId);
    }

    /**
     * Gets user by its email. Email is strictly matched.
     *
     * @param email
     * @return User.
     */
    @Override
    public User getUserByEmail(String email) {
        log.info("Attempting to find user with email {}", email);
        return userDao.getAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    /**
     * Get list of users by matching name. Name is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param name     Users name or it's part.
     * @param pageSize Pagination param. Number of users to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of users.
     */
    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        log.info("Attempting to get users with name {}", name);
        return userDao.getAll().stream().filter(user -> user.getName().contains(name)).collect(Collectors.toList());
    }

    /**
     * Creates new user. User id should be auto-generated.
     *
     * @param user User data.
     * @return Created User object.
     */
    @Override
    public User createUser(User user) {
        log.info("Attempting to create new user");
        return userDao.create(user);
    }

    /**
     * Updates user using given data.
     *
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    @Override
    public User updateUser(User user) {
        log.info("Attempting to update user with id {}", user.getId());
        userDao.delete(user.getId());
        return userDao.create(user);
    }

    /**
     * Deletes user by its id.
     *
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    @Override
    public boolean deleteUser(long userId) {
        log.info("Attempting to delete user with id {}", userId);
        return userDao.delete(userId);
    }
}
