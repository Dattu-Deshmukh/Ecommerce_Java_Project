package com.example.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Entity.ContactForm;

public interface ContactFormRepository extends MongoRepository<ContactForm, String>{

	ContactForm save(ContactForm contactForm);

	List<ContactForm> findByEmail(String email);

	List<ContactForm> findByMessageContainingIgnoreCase(String keyword);

	List<ContactForm> findAll();

}
