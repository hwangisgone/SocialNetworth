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
//	@Temporal(TemporalType.TIMESTAMP)
	private OffsetDateTime timePosted;

	@Column(name = "time_edited")
//	@Temporal(TemporalType.TIMESTAMP)
	private OffsetDateTime timeEdited;

	public Post() {

	}

	public Post(String content) {
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

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", timePosted=" + timePosted + ", timeEdited=" + timeEdited + "]";
	}
}
