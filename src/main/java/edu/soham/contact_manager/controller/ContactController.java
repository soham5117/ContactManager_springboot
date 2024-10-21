package edu.soham.contact_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import edu.soham.contact_manager.entity.Contact;
import edu.soham.contact_manager.entity.Response;
import edu.soham.contact_manager.service.ContactService;

@CrossOrigin(origins ="*")
@RestController
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping(value = "/contact")
	protected ResponseEntity<Response<Contact>> addContact(@RequestBody Contact contact) {
		Contact addedcontact = contactService.addContact(contact);
		Response<Contact>response= new Response<>();
		response.setMessage("new Contact is Added");
		response.setHttpStatusCode(HttpStatus.CREATED.value());
		response.setData(addedcontact);
		return new ResponseEntity<Response<Contact>>(response, HttpStatus.CREATED);
		
		
	}
	
	@PutMapping(value = "/contact")
	protected ResponseEntity<Response<Contact>> updateContact(@RequestBody Contact contact , @RequestParam int id) {
		Contact updatedcontact = contactService.updateContact(contact, id);
		Response<Contact> response =new Response<>();
		response.setMessage("Contact is Updated");
		response.setHttpStatusCode(HttpStatus.CREATED.value());
		response.setData(updatedcontact);
		return new ResponseEntity<Response<Contact>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/contact/{id}")
	protected ResponseEntity<Response<Contact>> deleteContact(@PathVariable int id) {
		Contact contactDeleted =contactService.deleteContact(id);
		Response<Contact> response = new Response<>();
		response.setMessage("Contact is Deleted");
		response.setHttpStatusCode(HttpStatus.OK.value());
		response.setData(contactDeleted);
		return new ResponseEntity<Response<Contact>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/contact/{id}")
	protected ResponseEntity<Response<Contact>> findContactById(@PathVariable int id) {
		Contact findContact = contactService.findContactById(id);
		Response<Contact> response= new Response<>();
		if (findContact != null) {
			response.setMessage("Contact Found");
			response.setHttpStatusCode(HttpStatus.FOUND.value());
			response.setData(findContact);
			return new ResponseEntity<Response<Contact>>(response, HttpStatus.OK);
			
		}else {
			response.setMessage("Contact is Not Found");
			response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Response<Contact>>(response, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "/contacts")
	protected ResponseEntity<Response<List<Contact>>> findAllContacts() {
	    List<Contact> allContacts = contactService.findAllContacts();
	    Response<List<Contact>> response = new Response<>();

	    if (!allContacts.isEmpty()) {
	        response.setMessage("Contacts Found");
	        response.setHttpStatusCode(HttpStatus.OK.value());
	        response.setData(allContacts);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        response.setMessage("No Contacts Found");
	        response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}

}
