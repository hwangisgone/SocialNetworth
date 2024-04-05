package com.example.purrpost.model;

import java.util.Date;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date timePosted;

	@Column(name = "time_edited")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeEdited;

	public Post() {

	}

	public Post(String content, Date timePosted) {
		this.content = content;
		this.timePosted = timePosted;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(Date timePosted) {
		this.timePosted = timePosted;
	}

	public Date getTimeEdited() {
		return timeEdited;
	}

	public void setTimeEdited(Date timeEdited) {
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
