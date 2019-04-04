package com.deliasoft.contactbook.service;

import com.deliasoft.contactbook.exception.*;
import com.deliasoft.contactbook.persistence.entity.Contact;
import com.deliasoft.contactbook.persistence.repository.ContactsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactBookService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ContactsRepository contactsRepository;

    public ContactBookService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public void addContact(Contact contact) throws ContactBookException {
        log.info("Adding new contact. Contact: " + contact.toString());

        verifyContact(contact, false);

        Contact savedContact = contactsRepository.save(contact);

        if (savedContact != null)
            log.info("Contact is saved successfully");
    }

    public void editContact(Contact contact) throws ContactBookException {
        log.info("Editing contact. Contact: " + contact.toString());

        verifyContact(contact, true);

        Contact savedContact = contactsRepository.save(contact);

        if (savedContact != null)
            log.info("Contact is edited successfully");
    }

    public void deleteContact(int contactId) throws ContactBookException {
        log.info("Deleting contact with id " + contactId);

        verifyContactExistsById(contactId);

        contactsRepository.deleteById(contactId);

        if (!contactsRepository.existsById(contactId))
            log.info("Contact is deleted successfully");
    }

    public List<Contact> getAllContacts() {
        log.info("Getting all contacts");

        return contactsRepository.findAll();
    }

    private void verifyContact(Contact contact, boolean verifyExists) throws ContactBookException {

        if (verifyExists)
            verifyContactExistsById(contact.getId());

        if (contact.getFirstName() == null || contact.getFirstName().isEmpty())
            throw new InvalidFirstNameException("First Name is required and can't be empty");

        if (contact.getLastName() == null || contact.getLastName().isEmpty())
            throw new InvalidLastNameException("Last Name is required and can't be empty");

        if (contact.getPhone() == null
                || contact.getPhone().isEmpty()
                || !contact.getPhone().matches("^\\+?\\d{10}$"))
            throw new InvalidPhoneNumberException("Phone number is required and should contains 10 digit numbers");
    }

    private void verifyContactExistsById(int contactId) throws ContactDoesNotExistException {
        if (!contactsRepository.existsById(contactId))
            throw new ContactDoesNotExistException(String.format("Contact with id: %s doesn't exist in database", contactId));
    }
}
