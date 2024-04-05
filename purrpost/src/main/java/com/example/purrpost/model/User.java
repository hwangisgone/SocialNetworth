package com.example.purrpost.model;

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
	
	@Column(name = "registation_date")
	private Date registation_date;
	

	public User() {
		
	}
	public User(String name_tag, String password) {
		this.nameTag = name_tag; 
		this.password = password;	

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

	


	
	

}
