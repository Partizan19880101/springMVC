package com.epam.training.dao.impl;

import com.epam.training.model.Ticket;
import com.epam.training.storage.Storage;
import com.epam.training.dao.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ticket dao.
 */
@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private Storage ticketStorage;

    private static final String STORAGE_PREFIX = "TICKET";

    @Override
    public List<Ticket> getAll() {
        List<Ticket> allTickets = new ArrayList<>();
        ticketStorage.getStorage().keySet().stream()
                .filter(key -> key.contains(STORAGE_PREFIX))
                .forEach(key -> allTickets.add((Ticket) ticketStorage.getStorage().get(key)));
        return allTickets;
    }

    @Override
    public boolean delete(long id) {
        String removeKey = STORAGE_PREFIX + id;
        return ticketStorage.getStorage().remove(removeKey) !=null;
    }

    @Override
    public Ticket create(Ticket ticket) {
        String key = STORAGE_PREFIX + ticket.getId();
        return (Ticket) ticketStorage.getStorage().put(key, ticket);
    }
}
