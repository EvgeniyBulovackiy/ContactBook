package com.deliasoft.contactbook.service;

import com.deliasoft.contactbook.persistence.entity.Contact;
import com.deliasoft.contactbook.persistence.repository.ContactsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactBookService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ContactsRepository contactsRepository;

    public ContactBookService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public void addContact(Contact contact) {
        log.info("Adding new contact. Contact: " + contact.toString());

        Contact savedContact = contactsRepository.save(contact);

        if (savedContact != null)
            log.info("Contact is saved successfully");
    }

    public void editContact(Contact contact) {
        log.info("Editing contact. Contact: " + contact.toString());

        Contact savedContact = contactsRepository.save(contact);

        if (savedContact != null)
            log.info("Contact is edited successfully");
    }

    public void deleteContact(int contactId) {
        log.info("Deleting contact with id " + contactId);

        contactsRepository.deleteById(contactId);

        log.info("Contact is deleted successfully");
    }

    public List<Contact> getAllContacts() {
        log.info("Getting all contacts");

        return contactsRepository.findAll();
    }
}
