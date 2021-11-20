package com.epam.training.controller;

import com.epam.training.dao.EventDao;
import com.epam.training.model.impl.EventImpl;
import lombok.extern.slf4j.Slf4j;
import com.epam.training.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.epam.training.service.EventService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.epam.training.util.Constant.*;

@Slf4j
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDao eventDao;

    @GetMapping()
    public ModelAndView getEvents() {
        ModelAndView modelAndView = new ModelAndView("users/users");
        modelAndView.addObject(MESSAGE, "All events: ");
        modelAndView.addObject("events", eventDao.getAll());
        return modelAndView;
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("event") EventImpl event) {
        return "events/new";
    }

    @PostMapping
    public ModelAndView createUser(@ModelAttribute("event") EventImpl event) {
        ModelAndView modelAndView = new ModelAndView("users/users");
        eventService.create(event);

        modelAndView.addObject("users", eventDao.getAll());
        modelAndView.addObject(MESSAGE, String.format
                ("Successfully created new event with title %s and date %s. Created event id : %s", event.getTitle(), event.getDate(), event.getId()));
        return modelAndView;
    }

    @PutMapping("/{id}")
    public ModelAndView updateEvent(@PathVariable long id,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String date) {
        ModelAndView modelAndView = new ModelAndView("entities");
        Event oldEvent = eventService.getEventById(id);
        if (Objects.nonNull(oldEvent)) {
            Event newEvent = new EventImpl(title, parseDate(date));
            newEvent = eventService.updateEvent(oldEvent, newEvent);
            modelAndView.addObject("entities", newEvent);
            modelAndView.addObject(MESSAGE, SUCCESSFUL_UPDATE_MESSAGE);
        } else {
            modelAndView.addObject(MESSAGE, FAILED_SEARCH_MESSAGE);
        }
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteEvent(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        boolean isDeleted = eventService.delete(id);
        if (isDeleted) {
            modelAndView.addObject(MESSAGE, SUCCESSFUL_DELETION_MESSAGE);
        } else {
            modelAndView.addObject(MESSAGE, FAILED_SEARCH_MESSAGE);
        }
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getEventById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        Event event = eventService.getEventById(id);
        if (Objects.nonNull(event)) {
            modelAndView.addObject("entities", event);
            modelAndView.addObject(MESSAGE, SUCCESSFUL_SEARCH_MESSAGE);
        } else {
            modelAndView.addObject(MESSAGE, SUCCESSFUL_SEARCH_MESSAGE);
        }
        return modelAndView;
    }

    @GetMapping("/title/{title}")
    public ModelAndView getEventsByTitle(@PathVariable String title,
                                         @RequestParam(required = false, defaultValue = "25") int pageSize,
                                         @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<Event> events = eventService.getEventsByTitle(title, pageSize, pageNum);
        if (!events.isEmpty()) {
            modelAndView.addObject("entities", events);
            modelAndView.addObject(MESSAGE, SUCCESSFUL_SEARCH_MESSAGE);
        } else {
            modelAndView.addObject(MESSAGE, SUCCESSFUL_SEARCH_MESSAGE);
        }
        return modelAndView;
    }

    @GetMapping("/date/{date}")
    public ModelAndView getEventsByDate(@PathVariable String date,
                                        @RequestParam(required = false, defaultValue = "25") int pageSize,
                                        @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<Event> events = eventService.getEventsForDay(parseDate(date), pageSize, pageNum);
        if (!events.isEmpty()) {
            modelAndView.addObject("entities", events);
            modelAndView.addObject(MESSAGE, SUCCESSFUL_SEARCH_MESSAGE);
        } else {
            modelAndView.addObject(MESSAGE, FAILED_SEARCH_MESSAGE);
        }
        return modelAndView;
    }

    private Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}