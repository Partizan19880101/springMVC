package com.epam.training.model;

/**
 * The type Ticket.
 */
public interface Ticket {

    /**
     * Gets id.
     *
     * @return the id
     */
    long getId();

    /**
     * Sets id.
     *
     * @param id the id
     */
    void setId(long id);

    /**
     * Gets event id.
     *
     * @return the event id
     */
    long getEventId();

    /**
     * Sets event id.
     *
     * @param eventId the event id
     */
    void setEventId(long eventId);

    /**
     * Gets user id.
     *
     * @return the user id
     */
    long getUserId();

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    void setUserId(long userId);

    /**
     * Gets category.
     *
     * @return the category
     */
    Category getCategory();

    /**
     * Sets category.
     *
     * @param category the category
     */
    void setCategory(Category category);

    /**
     * Gets place.
     *
     * @return the place
     */
    int getPlace();

    /**
     * Sets place.
     *
     * @param place the place
     */
    void setPlace(int place);
}
