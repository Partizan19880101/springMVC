package com.epam.training.dao.impl;

import com.epam.training.model.Event;
import com.epam.training.storage.Storage;
import com.epam.training.dao.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Event dao.
 */
@Repository
public class EventDaoImpl implements EventDao {

    @Autowired
    private Storage eventStorage;

    private final String STORAGE_PREFIX = "EVENT";

    @Override
    public List<Event> getAll() {
        List<Event> allEvents = new ArrayList<>();
        eventStorage.getStorage().keySet().stream()
                .filter(key -> key.contains(STORAGE_PREFIX))
                .forEach(key -> allEvents.add((Event) eventStorage.getStorage().get(key)));
        return allEvents;
    }

    @Override
    public boolean delete(long id) {
        String removeKey = STORAGE_PREFIX + id;
        return eventStorage.getStorage().remove(removeKey) != null;
    }

    @Override
    public Event create(Event event) {
        String key = STORAGE_PREFIX + event.getId();
        return (Event) eventStorage.getStorage().put(key, event);
    }

    @Override
    public Event get(long id) {
        String key = STORAGE_PREFIX + id;
        return (Event) eventStorage.getStorage().get(key);
    }
}
