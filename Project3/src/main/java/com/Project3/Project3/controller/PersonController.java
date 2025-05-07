package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.Person;
import com.Project3.Project3.service.PersonService;

//import com.Project3.Project3.model.User;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/person")
	@ResponseBody
	public List<Person> getUsers() {
		return personService.returnData();
	}

}
