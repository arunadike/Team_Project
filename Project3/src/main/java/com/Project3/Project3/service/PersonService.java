package com.Project3.Project3.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Person;

@Service
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

	public List<Person> returnData() {
		try {
			List<Person> ob = new ArrayList<Person>();
			ob.add(new Person("arun", 1));
			ob.add(new Person("far", 2));
			return ob;
		} catch (Exception e) {
			logger.error("Error fetching persons: {}", e.getMessage(), e);
			throw e;
		}
	}

}
