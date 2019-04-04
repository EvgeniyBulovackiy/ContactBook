package com.deliasoft.contactbook.persistence.repository;

import com.deliasoft.contactbook.persistence.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Integer> {
}
