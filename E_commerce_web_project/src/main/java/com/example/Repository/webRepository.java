// com.example.Repository.webRepository.java
package com.example.Repository;

import com.example.Entity.Details;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface webRepository extends MongoRepository<Details, String> {
    Details findByEmail(String email);
}
