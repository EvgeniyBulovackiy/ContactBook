package com.deliasoft.contactbook.service;

import com.deliasoft.contactbook.exception.*;
import com.deliasoft.contactbook.persistence.entity.Contact;
import com.deliasoft.contactbook.persistence.repository.ContactRepositoryTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class ContactBookServiceTest {
    private ContactBookService service;
    private ContactRepositoryTest repository;

    private final static String NULL = null;
    private final static String EMPTY = "";
    private final static String PHONE_NUMBER_LENGTH_MORE_THAN_TEN = "12345678910";
    private final static String PHONE_NUMBER_LENGTH_LESS_THAN_TEN = "12345678";
    private final static Integer NEW_CONTACT_ID = 6;
    private final static Integer EDIT_CONTACT_ID = 1;

    @BeforeMethod
    public void setUp() {
        repository = new ContactRepositoryTest();
        service = new ContactBookService(repository);
    }

    @Test(expectedExceptions = InvalidFirstNameException.class)
    public void testAddContactWhenFirstNameNull() throws ContactBookException {
        Contact contact = createContact();

        contact.setFirstName(NULL);

        service.addContact(contact);
    }

    @Test(expectedExceptions = InvalidFirstNameException.class)
    public void testAddContactWhenFirstNameIsEmpty() throws ContactBookException {
        Contact contact = createContact();

        contact.setFirstName(EMPTY);

        service.addContact(contact);
    }

    @Test(expectedExceptions = InvalidLastNameException.class)
    public void testAddContactWhenLastNameNull() throws ContactBookException {
        Contact contact = createContact();

        contact.setLastName(NULL);

        service.addContact(contact);
    }

    @Test(expectedExceptions = InvalidLastNameException.class)
    public void testAddContactWhenLastNameIsEmpty() throws ContactBookException {
        Contact contact = createContact();

        contact.setLastName(EMPTY);

        service.addContact(contact);
    }

    @Test(expectedExceptions = InvalidPhoneNumberException.class)
    public void testAddContactWhenPhoneNull() throws ContactBookException {
        Contact contact = createContact();

        contact.setPhone(NULL);

        service.addContact(contact);
    }

    @Test(expectedExceptions = InvalidPhoneNumberException.class)
    public void testAddContactWhenPhoneIsEmpty() throws ContactBookException {
        Contact contact = createContact();

        contact.setPhone(EMPTY);

        service.addContact(contact);
    }

    @Test(expectedExceptions = InvalidPhoneNumberException.class)
    public void testAddContactWhenPhoneLengthIsMoreThanTenDigitNumber() throws ContactBookException {
        Contact contact = createContact();

        contact.setPhone(PHONE_NUMBER_LENGTH_MORE_THAN_TEN);

        service.addContact(contact);
    }

    @Test(expectedExceptions = InvalidPhoneNumberException.class)
    public void testAddContactWhenPhoneLengthIsLessThanTenDigitNumber() throws ContactBookException {
        Contact contact = createContact();

        contact.setPhone(PHONE_NUMBER_LENGTH_LESS_THAN_TEN);

        service.addContact(contact);
    }

    @Test
    public void testAddContact() throws ContactBookException {
        Contact expectedContact = createContact();

        expectedContact.setId(0);

        service.addContact(expectedContact);

        Contact actualContact = repository.getContacts().get(NEW_CONTACT_ID);

        Assert.assertNotNull(actualContact, "Contacts isn't added to database");
        Assert.assertEquals(expectedContact.getFirstName(), actualContact.getFirstName(), "First Name is added incorrectly");
        Assert.assertEquals(expectedContact.getLastName(), actualContact.getLastName(), "Last Name is added incorrectly");
        Assert.assertEquals(expectedContact.getPhone(), actualContact.getPhone(), "Phone number is added incorrectly");
    }

    @Test(expectedExceptions = InvalidFirstNameException.class)
    public void testEditContactWhenFirstNameNull() throws ContactBookException {
        Contact contact = createContact();

        contact.setFirstName(NULL);

        service.editContact(contact);
    }

    @Test(expectedExceptions = InvalidFirstNameException.class)
    public void testEditContactWhenFirstNameIsEmpty() throws ContactBookException {
        Contact contact = createContact();

        contact.setFirstName(EMPTY);

        service.editContact(contact);
    }

    @Test(expectedExceptions = InvalidLastNameException.class)
    public void testEditContactWhenLastNameNull() throws ContactBookException {
        Contact contact = createContact();

        contact.setLastName(NULL);

        service.editContact(contact);
    }

    @Test(expectedExceptions = InvalidLastNameException.class)
    public void testEditContactWhenLastNameIsEmpty() throws ContactBookException {
        Contact contact = createContact();

        contact.setLastName(EMPTY);

        service.editContact(contact);
    }

    @Test(expectedExceptions = InvalidPhoneNumberException.class)
    public void testEditContactWhenPhoneNull() throws ContactBookException {
        Contact contact = createContact();

        contact.setPhone(NULL);

        service.editContact(contact);
    }

    @Test(expectedExceptions = InvalidPhoneNumberException.class)
    public void testEditContactWhenPhoneIsEmpty() throws ContactBookException {
        Contact contact = createContact();

        contact.setPhone(EMPTY);

        service.editContact(contact);
    }

    @Test(expectedExceptions = InvalidPhoneNumberException.class)
    public void testEditContactWhenPhoneLengthIsMoreThanTenDigitNumber() throws ContactBookException {
        Contact contact = createContact();

        contact.setPhone(PHONE_NUMBER_LENGTH_MORE_THAN_TEN);

        service.editContact(contact);
    }

    @Test(expectedExceptions = InvalidPhoneNumberException.class)
    public void testEditContactWhenPhoneLengthIsLessThanTenDigitNumber() throws ContactBookException {
        Contact contact = createContact();

        contact.setPhone(PHONE_NUMBER_LENGTH_LESS_THAN_TEN);

        service.editContact(contact);
    }

    @Test
    public void testEditContact() throws ContactBookException {
        Contact expectedContact = createContact();

        service.addContact(expectedContact);

        Contact actualContact = repository.getContacts().get(EDIT_CONTACT_ID);

        Assert.assertNotNull(actualContact, "Contacts isn't added to database");
        Assert.assertEquals(expectedContact.getFirstName(), actualContact.getFirstName(), "First Name is added incorrectly");
        Assert.assertEquals(expectedContact.getLastName(), actualContact.getLastName(), "Last Name is added incorrectly");
        Assert.assertEquals(expectedContact.getPhone(), actualContact.getPhone(), "Phone number is added incorrectly");
    }

    @Test(expectedExceptions = ContactDoesNotExistException.class)
    public void testDeleteContactIfContactDoesNotExist() throws ContactBookException {
        service.deleteContact(NEW_CONTACT_ID);
    }

    @Test
    public void testDeleteContact() throws ContactBookException {
        Contact contactBefore = repository.getContacts().get(EDIT_CONTACT_ID);

        Assert.assertNotNull(contactBefore, "Contact doesn't exist in database");

        service.deleteContact(EDIT_CONTACT_ID);

        Contact contactAfter = repository.getContacts().get(EDIT_CONTACT_ID);

        Assert.assertNull(contactAfter, "Contact doesn't exist in database");

    }

    @Test
    public void testGetAllContacts() {
        List<Contact> expectedContacts = new ArrayList<>(repository.getContacts().values());

        List<Contact> actualContacts = service.getAllContacts();

        Assert.assertEquals(expectedContacts, actualContacts, "All contacts is got incorrectly");
    }

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setId(EDIT_CONTACT_ID);
        contact.setFirstName("John");
        contact.setLastName("Dow");
        contact.setPhone("1908528565");

        return contact;
    }
}