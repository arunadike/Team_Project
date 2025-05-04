package com.Project3.Project3.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
import com.Project3.Project3.model.Contactus;
import com.Project3.Project3.model.Users;
import com.Project3.Project3.repository.ContactRepository;
import com.Project3.Project3.repository.UserRepository;
 
@Service
public class ContactusService {
 
	@Autowired
    private ContactRepository contactusRepository;
	
	@Autowired
	private UserRepository userRepository;
 
    public Contactus saveContact(Contactus contactus, Long userId) {
        // You can add any business logic here before saving, e.g., logging, sending an email
    	Users users = userRepository.findById(userId).orElseThrow();
 
        // 2. Associate the User with the Contactus entity
        contactus.setUsers(users);
 
        // 3. Add a commt to be saved (this will be part of the Contactus entity)
        contactus.setCommenting(contactus.getCommenting());
 
        // 4. Save the Contactus entity, which now includes the user association and the comment
        return contactusRepository.save(contactus);
    }

	public List<Contactus> get() {
		return contactusRepository.findAll();
	}
 
    // You can add other service methods as needed, e.g., fetching all contacts, etc.
}
 