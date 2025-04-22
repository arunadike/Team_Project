package com.Project3.Project3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Person;

@Service
public class PersonService {

	public List<Person> returnData() {
		// TODO Auto-generated method stub
		 List<Person> ob=new ArrayList<Person>();
		 ob.add(new Person("arun",1));
		 ob.add(new Person("far",2));
		 return ob;
		//return null;
	}
	
	

}
