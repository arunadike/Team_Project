package com.Project3.Project3.controller;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.Project3.Project3.model.Contactus;
import com.Project3.Project3.service.ContactusService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactusController {
	@Autowired
	private ContactusService contactService;
	
    private static final Logger logger = LoggerFactory.getLogger(ContactusController.class); // Corrected logger name

	
	@PostMapping("/submit")
    public ResponseEntity<Contactus> submitContactForm(@Valid @RequestBody Contactus contact) {
        logger.info("Received contact form submission: {}", contact);
        try {
            Contactus savedContact = contactService.saveContact(contact, contact.getUsers().getUserId());
            logger.info("Contact form submitted successfully.  Contact ID: {}", savedContact.getContactId());
            return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Failed to submit contact form: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getComments")
    public ResponseEntity<List<Contactus>> getComments() {
        logger.info("Received request to get all contact form submissions");
        try {
            List<Contactus> comments = contactService.get();
            logger.info("Successfully retrieved {} contact form submissions.", comments.size());
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Failed to retrieve contact form submissions: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
}