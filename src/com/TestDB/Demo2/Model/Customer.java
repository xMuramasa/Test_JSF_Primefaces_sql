package com.TestDB.Demo2.Model;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", email=" + email + ", gender=" + gender
				+ ", income=" + income + ", birthday=" + birthday + "]";
	}

	private Long id;
	private String firstName;
	private String email;
	private String gender;
	private int income;
	private int age;
	private Date birthday;

	public Customer() {

	}
	
	public Customer(Long id, String firstName, String email, String gender, int income, int age, Date birthday) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.gender = gender;
		this.income = income;
		this.birthday = birthday;
		this.age = age;
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}