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
	
	@JsonProperty(value="consumer_Employee_No")
    private Integer consumer_Employee_No;


	private String name;
	private String email;
	private String confirmPassword;
	
	private  UserType roll;
	
	private Address add;
	private Bankdeatils bank;
    private List<Bill> bills= new ArrayList<>();
	
	
    public void setBank(Bankdeatils bank) {
		this.bank = bank;
	}

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
	
   @JsonIgnore
  @Column (name="Consumer_Employee_No",unique =true)
	public Integer getConsumer_Employee_No() {
		return consumer_Employee_No;
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
	
	public void setConsumer_Employee_No(Integer consumer_Employee_No) {
		this.consumer_Employee_No = consumer_Employee_No;
	}
	
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

	//******************************************************
	public User(Integer id,  String name, String email,String password) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		
	
	}
	
	public User(Address add)
	{
		this.add=add;
	}
	public User(Bankdeatils b)
	{
		this.bank=b;
	}
	//******************************************************
	/*
	 * Adder and remover Methods
	 */
	

	public User(Integer id, String password, String name, String email, String confirmPassword, UserType roll,
			Address add, Bankdeatils bank, List<Bill> bills) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.confirmPassword = confirmPassword;
		this.roll = roll;
		this.add = add;
		this.bank = bank;
		this.bills = bills;
	}

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
