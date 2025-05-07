package com.Project3.Project3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "contacts")

public class Contactus {

	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commenting_seq")
	@SequenceGenerator(name = "commenting_seq", sequenceName = "commenting_SEQ", allocationSize = 1)
	private Long contactId;

	@ManyToOne
	@JoinColumn(name = "userid", nullable = false)
	private Users users;

	@NotEmpty(message = "Comment cannot be empty")
	
	private String commenting;
	
	public Contactus(Long contactId, Users users, @NotEmpty(message = "Comment cannot be empty") String commenting) {
		super();
		this.contactId = contactId;
		this.users = users;
		this.commenting = commenting;
	}

	public Contactus() {
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getCommenting() {
		return commenting;
	}

	public void setCommenting(String commenting) {
		this.commenting = commenting;
	}



}
