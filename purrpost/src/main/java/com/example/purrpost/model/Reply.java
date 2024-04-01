package com.example.purrpost.model;

import jakarta.persistence.*; 
@Entity
@IdClass(ReplyId.class)
@Table(name = "reply_post")
public class Reply {
	
    @Id
    private long parentId;

    @Id
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
