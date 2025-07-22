package com.hyperlocal.events.controller;

import com.hyperlocal.events.model.Event;
import com.hyperlocal.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        
        if (eventRepository.count() == 0) {
            
            eventRepository.save(new Event("Community Clean-Up", "Help tidy up the local park.", "Central Park", "2025-08-01", "09:00 AM" ));
            
           
            eventRepository.save(new Event("Book Drive", "Donate books for the community library.", "Town Hall", "2025-07-15", "11:00 AM"));
            
            
            eventRepository.save(new Event("Urgent: Lost Dog Search", "Help find a lost golden retriever named Buddy.", "Rose Garden", "2025-07-20", "06:00 PM"));
            
            
            eventRepository.save(new Event("Weekend Farmers Market", "Fresh produce and local goods.", "Exhibition Ground", "2025-07-26", "10:00 AM"));
        }

        model.addAttribute("events", eventRepository.findAll());
        return "index"; // This corresponds to 'index.html'
    }

    /**
     * Shows the page with the form for creating a new event.
     */
    @GetMapping("/events/create")
    public String showCreateForm(Model model) {
        // We pass an empty event object to the form for data binding
        model.addAttribute("event", new Event());
        return "create-event"; // Name of the 'create-event.html' file
    }

    /**
     * Processes the form submission from the create event page.
     */
    @PostMapping("/events/add")
    public String addEvent(@ModelAttribute Event event) {
        // The @ModelAttribute annotation binds the form data to the event object
        eventRepository.save(event);
        return "redirect:/"; // Redirect to the homepage to see the new event
    }
}