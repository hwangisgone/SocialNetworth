package com.example.purrpost.model;

import java.time.Instant;
import java.util.Date;

// for Spring Boot 3
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "social_user")
public class SocialUser{

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
	
	@Column(name = "avatar_url")
	private String avatarUrl;

	@Column(name = "registration_date")
	private Date registrationDate;
	
	@Column(name = "follow_count")
	private int followingCount;

	@Column(name = "followed_count")
	private int followerCount;


	public SocialUser() {

	}

	public SocialUser(String nameTag, String password, String name, String email) {
		super();
		this.nameTag = nameTag;
		this.password = password;
		this.name = name;
		this.email = email;
		
		
		this.bio = "No bio yet.";
		this.registrationDate = Date.from(Instant.now());
		this.avatarUrl = "https://i.imgur.com/9l1A4OS.jpeg";
		this.role = "user";
	}

	public void setDefaultTestUser() {
		this.name = "TEST NAME";
		this.email = "test@email.com";
		this.registrationDate = Date.from(Instant.now());
		this.role = "user";
	}

	public String getNameTag() {
		return nameTag;
	}

	public void setNameTag(String nameTag) {
		this.nameTag = nameTag;
	}

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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public long getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	public int getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	@Override
	public String toString() {
		return "User [nameTag=" + nameTag + ", registrationDate=" + "]";
	}
}
