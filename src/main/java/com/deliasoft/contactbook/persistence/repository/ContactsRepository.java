package com.deliasoft.contactbook.persistence.repository;

import com.deliasoft.contactbook.persistence.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "contacts", path = "contacts")
public interface ContactsRepository extends JpaRepository<Contact, Integer> {
}
