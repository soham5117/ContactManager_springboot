package edu.soham.contact_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.soham.contact_manager.entity.Contact;
import edu.soham.contact_manager.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	public Contact addContact(Contact contact) {
		return contactRepository.save(contact);
	}

	public Contact updateContact(Contact contact, int id) {
		Contact contact2 = contactRepository.findById(id).get();
		contact2.setName(contact.getName());
		contact2.setEmail(contact.getEmail());
		contact2.setMobile(contact.getMobile());
		contact2.setPhoto(contact.getPhoto());
		contact2.setCompany(contact.getCompany());
		contact2.setGroupId(contact.getGroupId());
		contact2.setTitle(contact.getTitle());
		return contactRepository.save(contact2);
	}

	public Contact deleteContact(int id) {
		Contact contact =contactRepository.findById(id).get();
		contactRepository.deleteById(id);
		return contact;
	}

	public Contact findContactById(int id) {		
		Contact contact = contactRepository.findById(id).get();
		
			return contact;
		
	}

	public List<Contact> findAllContacts() {
		List<Contact> all = contactRepository.findAll();
		return all;
	}
	
	
}
