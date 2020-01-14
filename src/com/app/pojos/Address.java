package com.app.pojos;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="address")
public class Address extends AbstractEntity {
	
	private String city,state;
	private User users;
	

	public Address() {
		
	}
	public Address(String city, String state, String country, String cellNo) {
	   super();
		this.city = city;
		this.state = state;
		
	}
	/*
	 * ==========================================
	 * Mapping
	 * ===============================================
	 */
	

	@OneToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	@JsonProperty(value = "no")
	public User getUsers() {
		return users;
	}
	
	@Column(length=20)
	public String getCity() {
		return city;
	}

	@Column(length=20)
	public String getState() {
		return state;
	}

	
	
	
	
	/*
	 * Setters
	 */
	public void setUsers(User users) {
		this.users = users;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
