package com.example.purrpost.model;

import com.example.purrpost.model.compositeid.ReactionId;

import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "reaction")
@IdClass(ReactionId.class)
public class Reaction {

	@Id
	@Column(name = "user_id")
	private long userId;

	@Id
	@Column(name = "post_id")
	private long postId;

	@Column(name = "reaction_type")
	private char reactionType;

	public Reaction() {
	}

	public Reaction(long userId, long postId, char reactionType) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.reactionType = reactionType;
	}

	@Override
	public String toString() {
		return "Reaction [user_id=" + userId + ", post_id=" + postId + ", reaction_type=" + reactionType + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public char getReactionType() {
		return reactionType;
	}

	public void setReactionType(char reactionType) {
		this.reactionType = reactionType;
	}
}
