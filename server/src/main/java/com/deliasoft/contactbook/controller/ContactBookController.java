package com.deliasoft.contactbook.controller;

import com.deliasoft.contactbook.exception.ContactBookException;
import com.deliasoft.contactbook.persistence.entity.Contact;
import com.deliasoft.contactbook.service.ContactBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/contact_book")
public class ContactBookController {

    private ContactBookService service;

    public ContactBookController(ContactBookService service) {
        this.service = service;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public void addContact(@RequestBody Contact contact) throws ContactBookException {
        service.addContact(contact);
    }

    @RequestMapping(value = "/contact", method = RequestMethod.PUT)
    public void editContact(@RequestBody Contact contact) throws ContactBookException {
        service.editContact(contact);
    }

    @RequestMapping(value = "/contact/{contactId}", method = RequestMethod.DELETE)
    public void deleteContact(@PathVariable int contactId) throws ContactBookException {
        service.deleteContact(contactId);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public List<Contact> getAllContacts() {
        return service.getAllContacts();
    }
}
