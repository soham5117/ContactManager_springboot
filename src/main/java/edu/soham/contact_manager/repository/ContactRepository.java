package edu.soham.contact_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.soham.contact_manager.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
