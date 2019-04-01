package com.deliasoft.contactbook.service;

import com.deliasoft.contactbook.persistence.entity.Contact;
import com.deliasoft.contactbook.persistence.repository.ContactsRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ContactBookServiceTest {

    @Test
    public void testAddContact() {
        ContactsRepository repository = Mockito.mock(ContactsRepository.class);
        ContactBookService service = new ContactBookService(repository);

        Contact contact = createContact();

        service.addContact(contact);

        Mockito.verify(repository).save(contact);
    }

    @Test
    public void testEditContact() {
        ContactsRepository repository = Mockito.mock(ContactsRepository.class);
        ContactBookService service = new ContactBookService(repository);

        Contact contact = createContact();

        service.editContact(contact);

        Mockito.verify(repository).save(contact);
    }

    @Test
    public void testDeleteContact() {
        ContactsRepository repository = Mockito.mock(ContactsRepository.class);
        ContactBookService service = new ContactBookService(repository);

        int id = 1;

        service.deleteContact(id);

        Mockito.verify(repository).deleteById(id);
    }

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setFirstName("John");
        contact.setLastName("Dow");
        contact.setPhone("19085285656");

        return contact;
    }
}