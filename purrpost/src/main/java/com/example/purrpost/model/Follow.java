package com.example.purrpost.model;

import com.example.purrpost.model.compositeid.FollowId;
import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "follow")
@IdClass(FollowId.class)
public class Follow {

	@Id
	@Column(name = "user_id")
	private long userId;

	@Id
	@Column(name = "follower_id")
	private long followerId;

	public Follow() {
	}

	public Follow(long userId, long followerId) {
		super();
		this.userId = userId;
		this.followerId = followerId;
	}

	@Override
	public String toString() {
		return "Reaction [user_id=" + userId + ", follower_id=" + followerId + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getfollowerId() {
		return followerId;
	}

	public void setfollowerId(long followerId) {
		this.followerId = followerId;
	}
}
