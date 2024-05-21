package com.example.purrpost.model;


import com.example.purrpost.model.compositeid.ReplyId;

import jakarta.persistence.*; // for Spring Boot 3


@Entity
@Table(name = "reply_post")
@IdClass(ReplyId.class)
public class Reply {
	
	@Id
    @Column(name = "post_id_parent")
	private long parentId;
	
	@Id
    @Column(name = "post_id_child")
    private long childId;


    // Other fields and methods

    public Reply() {
    }

    public Reply(long parentId, long childId) {
		this.parentId = parentId;
		this.childId = childId;
	}

	public long getParentId() {
		return parentId;
	}

    public long getChildId() {
		return childId;
	}

	public void setIdParent(long parentId) {
		this.parentId = parentId;
	}

    public void setIdChild(long childId) {
		this.childId = childId;
	}

	
	@Override
	public String toString() {
		return "Reply [parentId=" + parentId + ", childId=" + childId + "]";
	}
}
