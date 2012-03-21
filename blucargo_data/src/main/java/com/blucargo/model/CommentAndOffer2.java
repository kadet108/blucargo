package com.blucargo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="commentandoffer2")
public class CommentAndOffer2 extends Comment{
	
	private static final long serialVersionUID = -8826450526853859902L;

	private String contact;
	private String username;
	private String owner;

	public CommentAndOffer2(){}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
