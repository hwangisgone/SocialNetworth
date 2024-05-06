package com.example.purrpost.model;

import java.time.OffsetDateTime;

import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "post")
public class Post {

//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long post_id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Use database side to generate
	@Column(name = "post_id", insertable = false)
	private long id;
	
	@Column(name = "content")
	private String content;

	@Column(name = "time_posted", updatable = false)
	private OffsetDateTime timePosted;

	@Column(name = "time_edited")
	private OffsetDateTime timeEdited;
	
	@Column(name = "like_count")
	private int likeCount;
	
	@Column(name = "reply_count")
	private int replyCount;
	
	@Column(name = "share_count")
	private int shareCount;
	
	@Column(name = "user_id")
	private long userId;
	
	public Post() {

	}

	public Post(long userId, String content) {
		this.userId = userId;
		this.content = content;
		this.timePosted = OffsetDateTime.now();
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public OffsetDateTime getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(OffsetDateTime timePosted) {
		this.timePosted = timePosted;
	}

	public OffsetDateTime getTimeEdited() {
		return timeEdited;
	}

	public void setTimeEdited(OffsetDateTime timeEdited) {
		this.timeEdited = timeEdited;
	}
	
	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public long getUserId() {
		return userId;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", timePosted=" + timePosted + ", timeEdited=" + timeEdited + "]";
	}
}
