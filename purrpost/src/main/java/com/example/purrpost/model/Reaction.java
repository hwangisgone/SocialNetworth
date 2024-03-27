package com.example.purrpost.model;

import com.example.purrpost.model.compositeid.InteractionId;

import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "reaction")
@IdClass(InteractionId.class)
public class Reaction {

	@Id 
	@Column(name = "user_id")
	private int userId;
	
	@Id 
	@Column(name = "post_id")
	private int postId;
	
	@Column(name = "reaction_type")
	private char reactionType;
	
	public Reaction() {
	}
	
	public Reaction(int userId, int postId, char reactionType) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.reactionType = reactionType;
	}

	
	@Override
	public String toString() {
		return "Reaction [user_id=" + userId + ", post_id=" + postId + ", reaction_type=" + reactionType + "]";
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public char getReactionType() {
		return reactionType;
	}

	public void setReactionType(char reactionType) {
		this.reactionType = reactionType;
	}
}
