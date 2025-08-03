package com.example.Service;

import com.example.Entity.ContactForm;
import com.example.Repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactFormService {

    @Autowired
    private ContactFormRepository contactRepo;

    public ContactForm saveContactForm(ContactForm contactForm) {
        return contactRepo.save(contactForm);
    }

    public List<ContactForm> getContactFormsByEmail(String email) {
        return contactRepo.findByEmail(email);
    }

    public List<ContactForm> searchByMessage(String keyword) {
        return contactRepo.findByMessageContainingIgnoreCase(keyword);
    }

    public List<ContactForm> getAllContactForms() {
        return contactRepo.findAll();
    }
}
