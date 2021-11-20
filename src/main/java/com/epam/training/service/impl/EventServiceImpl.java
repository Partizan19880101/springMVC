package com.epam.training.service.impl;

import com.epam.training.dao.EventDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.epam.training.model.Event;
import com.epam.training.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Event service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventDao eventDao;

    /**
     * Gets event by its id.
     *
     * @param eventId
     * @return Event.
     */
    @Override
    public Event getEventById(long eventId) {
        log.info("Attempting to get event by id {}", eventId);
        return eventDao.get(eventId);
    }

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param title    Event title or it's part.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        log.info("Attempting to get all events by title {}", title);
        return eventDao.getAll().stream().filter(event -> event.getTitle().contains(title)).collect(Collectors.toList());
    }

    /**
     * Get list of events for specified day.
     * In case nothing was found, empty list is returned.
     *
     * @param day      Date object from which day information is extracted.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        log.info("Attempting to get all the events for day {}", day.toString());
        return eventDao.getAll().stream().filter(event -> event.getDate().equals(day)).collect(Collectors.toList());
    }

    /**
     * Creates new event. Event id should be auto-generated.
     *
     * @param event Event data.
     */
    @Override
    public Event create(Event event) {
        log.info("Attempting to create new event with name {}", event.getTitle());
        return eventDao.create(event);
    }

    /**
     * Updates event using given data.
     *
     * @param oldEvent Event data for update. Should have id set.
     * @return Updated Event object.
     */
    @Override
    public Event updateEvent(Event oldEvent, Event newEvent) {
        log.info("Attempting to update event with id {}", oldEvent.getId());
        long oldEventId = oldEvent.getId();
        eventDao.delete(oldEventId);
        Event addedEvent = eventDao.create(newEvent);
        addedEvent.setId(oldEventId);
        return addedEvent;
    }

    /**
     * Deletes event by its id.
     *
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    @Override
    public boolean delete(long eventId) {
        log.info("Attempting to delete event with id {}", eventId);
        return eventDao.delete(eventId);
    }
}
