package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
public class User 
{

	@JsonProperty(value="id")
	private Integer id;
	private String password;

	private String name;
	private String email;
	private String confirmPassword;
	
	private  UserType roll;
	
	private Address add;
	private Bankdeatils bank;
    private List<Bill> bills= new ArrayList<>();
	
	
    public User() {
		
	}
/*
 * ===================================================================
 * Mapping
 * =====================================================================
 */
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	public Integer getId() {
		return id;
	}

    
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	public Address getAdd() {
		return add;
	}


	@OneToMany(mappedBy = "userBillDeatils",
			cascade = CascadeType.ALL ,fetch=FetchType.EAGER , orphanRemoval = true)
	public List<Bill> getBills() {
		return bills;
	}

	@Embedded
	public Bankdeatils getBank() {
		return bank;
	}
	
	/*
	 * =================================================
	 * Other GEtters
	 * =================================================
	 */
	@Enumerated(EnumType.STRING)
	@Column(length=20,name="user_type")
	public UserType getRoll() {
		return roll;
	}
	
	@Transient //skip from persistence--no col generation
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	
	
	
	/*
	 * =================================================
	 * Other Setters
	 * =================================================
	 */
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setAdd(Address add) {
		this.add = add;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRoll(UserType roll) {
		this.roll = roll;
	}

	
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", roll=" + roll
				+ ", add=" + add + ", getAdd()=" + getAdd() + ", getId()=" + getId() + ", getRoll()=" + getRoll()
				+ ", getPassword()=" + getPassword() + ", getName()=" + getName() + ", getEmail()=" + getEmail()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
	public User(Integer id, String password, String name, String email, UserType roll, Address add, Bankdeatils bank,
			List<Bill> bills) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.roll = roll;
		this.add = add;
		this.bank = bank;
		this.bills = bills;
	}
	
	/*
	 * Adder and remover Methods
	 */
	

	public void addAddress(Address a) {
		// student ---> address
		this.add = a;
		a.setUsers(this);// address ---> stduent
	}

	public void removeAddress(Address a) {
		add = null;
		a.setUsers(null);
	}
	
	
	public void addBill(Bill b) {
		bills.add(b);
		b.setUserBillDeatils(this);// bill-->user

	}

	public void removeBill(Bill b) {
		bills.remove(b);
		b.setUserBillDeatils(null);
	}
	
}
