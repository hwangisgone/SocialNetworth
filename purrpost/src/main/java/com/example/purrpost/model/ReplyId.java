package com.example.purrpost.model;

import java.io.Serializable;
import jakarta.persistence.*; 

@Embeddable
public class ReplyId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "post_id_parent")
    private long parentId;

    @Column(name = "post_id_child")
    private long childId;

    public ReplyId() {
    }

    public ReplyId(long parentId, long childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReplyId replyId = (ReplyId) o;

        if (parentId != replyId.parentId) return false;
        return childId == replyId.childId;
    }

    @Override
    public int hashCode() {
        int result = (int) (parentId ^ (parentId >>> 32));
        result = 31 * result + (int) (childId ^ (childId >>> 32));
        return result;
    }
}
