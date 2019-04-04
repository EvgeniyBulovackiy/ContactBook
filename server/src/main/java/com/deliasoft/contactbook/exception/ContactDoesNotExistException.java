package com.deliasoft.contactbook.exception;

public class ContactDoesNotExistException extends ContactBookException {

    public ContactDoesNotExistException(String message) {
        super(message);
    }
}
