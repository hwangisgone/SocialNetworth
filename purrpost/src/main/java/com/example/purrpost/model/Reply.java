package com.example.purrpost.model;

import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "reply_post")
public class Reply {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "post_id_parent")
	private long id_parent;

	@Column(name = "post_id_child")
	private long id_child;


	public Reply() {

	}

	public Reply(long id_parent, long id_child) {
		this.id_parent = id_parent;
		this.id_child = id_child;
	}

	public long getIdParent() {
		return id_parent;
	}

	public long getIdChild() {
		return id_child;
	}

	public void setIdParent(long id_parent) {
		this.id_parent = id_parent;
	}

	public void setIdChild(long id_child) {
		this.id_child = id_child;
	}

	public long getId() {
		return id;
	}

	
	@Override
	public String toString() {
		return "Reply [id=" + id + ", id_parent=" + id_parent + ", id_child=" + id_child + "]";
	}
}
