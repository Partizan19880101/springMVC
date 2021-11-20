package com.epam.training.model;

import java.util.Date;

/**
 * The type Event.
 */
public interface Event {

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
     * Gets title.
     *
     * @return the title
     */
    String getTitle();

    /**
     * Sets title.
     *
     * @param title the title
     */
    void setTitle(String title);

    /**
     * Gets date.
     *
     * @return the date
     */
    Date getDate();

    /**
     * Sets date.
     *
     * @param date the date
     */
    void setDate(Date date);
}
