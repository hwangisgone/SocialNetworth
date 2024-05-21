package com.example.purrpost.model.compositeid;

import java.io.Serializable;
import java.util.Objects;

public class ReplyId implements Serializable {
    private static final long serialVersionUID = 1L;

    private long parentId;
    private long childId;

	@Override
	public int hashCode() {
		return Objects.hash(childId, parentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		ReplyId other = (ReplyId) obj;
		return childId == other.childId && parentId == other.parentId;
	}

}
