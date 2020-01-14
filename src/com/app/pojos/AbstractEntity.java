package com.app.pojos;

import javax.persistence.*;

@MappedSuperclass
public class AbstractEntity {
	private Integer id;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//def generation strategy=AUTO--native to DB(eg : Oracle -- sequence 
	// MySql --table -- hibernate_sequence
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
