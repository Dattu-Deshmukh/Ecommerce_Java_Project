package com.example.Repository;

import java.util.List;

import com.example.Entity.ContactForm;

public interface ContactFormRepository {

	ContactForm save(ContactForm contactForm);

	List<ContactForm> findByEmail(String email);

	List<ContactForm> findByMessageContainingIgnoreCase(String keyword);

	List<ContactForm> findAll();

}
