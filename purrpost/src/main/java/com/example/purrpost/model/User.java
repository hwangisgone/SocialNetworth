package com.example.purrpost.model;

<<<<<<< HEAD
=======
import java.time.Instant;
>>>>>>> 3bff5ee8c13e2728af7abbb779562b3bb7af8259
import java.util.Date;

import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "social_user")
public class User{

//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long post_id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Use database side to generate
	@Column(name = "user_id", insertable = false)
	private long userId;
	
	@Column(name = "name_tag")
	private String nameTag;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "role")
	private String role;

	@Column(name = "phone")
	private String phone;
	
<<<<<<< HEAD
	@Column(name = "registation_date")
	private Date registation_date;
=======
	@Column(name = "registration_date")
	private Date registrationDate;
>>>>>>> 3bff5ee8c13e2728af7abbb779562b3bb7af8259
	

	public User() {
		
	}
	public User(String name_tag, String password) {
		this.nameTag = name_tag; 
<<<<<<< HEAD
		this.password = password;	

	}
	
	public User(long userId, String nameTag, String password, String name, String email, String bio, String role,
			String phone, Date registation_date) {
		super();
		this.userId = userId;
		this.nameTag = nameTag;
		this.password = password;
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.role = role;
		this.phone = phone;
		this.registation_date = registation_date;
	}
	public String getNametag() {
		return nameTag;
	}
	
	public String getPassword() {
		return password;
		
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
=======
		this.password = password;
	}

	public void setDefaultUser() {
		this.registrationDate = Date.from(Instant.now());
		this.role = "user";
	}
	
	
>>>>>>> 3bff5ee8c13e2728af7abbb779562b3bb7af8259
	public String getNameTag() {
		return nameTag;
	}
	public void setNameTag(String nameTag) {
		this.nameTag = nameTag;
	}
<<<<<<< HEAD
=======
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
>>>>>>> 3bff5ee8c13e2728af7abbb779562b3bb7af8259
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
<<<<<<< HEAD
	public Date getRegistation_date() {
		return registation_date;
	}
	public void setRegistation_date(Date registation_date) {
		this.registation_date = registation_date;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	


	
	

=======
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public long getUserId() {
		return userId;
	}
>>>>>>> 3bff5ee8c13e2728af7abbb779562b3bb7af8259
}
