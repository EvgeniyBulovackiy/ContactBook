package com.deliasoft.contactbook.persistence.repository;

import com.deliasoft.contactbook.persistence.entity.Contact;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

public class ContactRepositoryTest implements ContactsRepository {

    private Map<Integer, Contact> contacts = initContacts();
    private static Integer id = 6;

    public Map<Integer, Contact> getContacts() {
        return contacts;
    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(contacts.values());
    }

    @Override
    public List<Contact> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Contact> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public <S extends Contact> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Contact> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Contact> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Contact getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Contact> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Contact> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Contact> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Contact> S save(S s) {

        if (s.getId() == 0)
            s.setId(id++);

        return (S) contacts.put(s.getId(), s);
    }

    @Override
    public Optional<Contact> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return contacts.containsKey(integer);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
        contacts.remove(integer);
    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public void deleteAll(Iterable<? extends Contact> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Contact> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Contact> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Contact> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Contact> boolean exists(Example<S> example) {
        return false;
    }

    private Map initContacts() {
        Map<Integer, Contact> initContacts = new HashMap();

        initContacts.put(1, new Contact(1, "First1", "Last1", "0505548571"));
        initContacts.put(2, new Contact(2, "First2", "Last2", "0505548572"));
        initContacts.put(3, new Contact(3, "First3", "Last3", "0505548573"));
        initContacts.put(4, new Contact(4, "First4", "Last4", "0505548574"));
        initContacts.put(5, new Contact(5, "First5", "Last5", "0505548575"));

        return initContacts;
    }
}
