package com.example.Repository;

import com.example.Entity.ContactForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ContactFormRepository extends MongoRepository<ContactForm, String> {
    List<ContactForm> findByEmail(String email);
    List<ContactForm> findByMessageContainingIgnoreCase(String keyword);
}
