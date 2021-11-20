package com.epam.training.service.impl;


import com.epam.training.model.impl.TicketImpl;
import com.epam.training.dao.TicketDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.User;
import com.epam.training.service.TicketService;
import com.epam.training.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Ticket service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Autowired
    private final TicketDao ticketDao;

    /**
     * Book ticket for a specified event on behalf of specified user.
     *
     * @param user   User Id.
     * @param event  Event Id.
     * @param place    Place number.
     * @param category Service category.
     * @return Booked ticket object.
     * @throws IllegalStateException if this place has already been booked.
     */
    @Override
    public Ticket bookTicket(User user, Event event, int place, Category category) {
        log.info("Attempting to book new ticket for {} event for user with id {}", user.getId(), event.getId());
        return ticketDao.create(new TicketImpl(event.getId(), user.getId(), category, place));
    }

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     *
     * @param user     User
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        log.info("Attempting to get all tickets of user with id {}", user.getId());
        return ticketDao.getAll().stream().filter(ticket -> ticket.getUserId()== user.getId()).collect(Collectors.toList());
    }

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     *
     * @param event    Event
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        log.info("Attempting to get all booked tickets for event {}", event);
        return ticketDao.getAll().stream().filter(ticket -> ticket.getEventId()==event.getId()).collect(Collectors.toList());
    }

    /**
     * Cancel ticket with a specified id.
     *
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    @Override
    public boolean cancelTicket(long ticketId) {
        log.info("Attempting to cancel ticket with id {}", ticketId);
        return ticketDao.delete(ticketId);
    }
}
