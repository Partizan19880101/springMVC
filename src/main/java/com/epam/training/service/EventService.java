package com.epam.training.service;

import com.epam.training.model.Event;

import java.util.Date;
import java.util.List;

/**
 * The interface Event service.
 */
public interface EventService {

    /**
     * Gets event by its id.
     *
     * @param eventId the event id
     * @return Event. event by id
     */
    Event getEventById(long eventId);

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param title    Event title or it's part.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    /**
     * Get list of events for specified day.
     * In case nothing was found, empty list is returned.
     *
     * @param day      Date object from which day information is extracted.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    /**
     * Creates new event. Event id should be auto-generated.
     *
     * @param event Event data.
     * @return the event
     */
    Event create(Event event);

    /**
     * Updates event using given data.
     *
     * @param oldEvent Event data for update. Should have id set.
     * @return Updated Event object.
     */
    Event updateEvent(Event oldEvent, Event newEvent);

    /**
     * Deletes event by its id.
     *
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    boolean delete(long eventId);
}
