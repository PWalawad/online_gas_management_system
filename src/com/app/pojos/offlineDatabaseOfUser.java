package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "offlineDatabaseOfUser")
public class offlineDatabaseOfUser 
{
	@JsonProperty(value="id")
	private Integer id;
	public offlineDatabaseOfUser() {
		// TODO Auto-generated constructor stub
	}
	
	@JsonProperty(value="consumer_Employee_No")
    private Integer consumer_Employee_No;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	public Integer getId() {
		return id;
	}
	
   @JsonIgnore
  @Column (name="consumer_Employee_No",unique =true)
	public Integer getConsumer_Employee_No() {
		return consumer_Employee_No;
	}

public offlineDatabaseOfUser(Integer id, Integer consumer_Employee_No) {
	super();
	this.id = id;
	this.consumer_Employee_No = consumer_Employee_No;
}

public void setId(Integer id) {
	this.id = id;
}

public void setConsumer_Employee_No(Integer consumer_Employee_No) {
	this.consumer_Employee_No = consumer_Employee_No;
}


}
