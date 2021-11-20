package com.epam.training.model.impl;

import lombok.Data;
import com.epam.training.model.Category;
import com.epam.training.model.Ticket;

/**
 * The type Ticket.
 */
@Data
public class TicketImpl implements Ticket {

    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;

    /**
     * Instantiates a new Ticket.
     *
     * @param eventId  the event id
     * @param userId   the user id
     * @param category the category
     * @param place    the place
     */
    public TicketImpl(long eventId, long userId, Category category, int place) {
        this.id = id++;
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }
}
