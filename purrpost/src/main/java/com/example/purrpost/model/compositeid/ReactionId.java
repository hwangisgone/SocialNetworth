package com.example.purrpost.model.compositeid;

import java.io.Serializable;
import java.util.Objects;

public class ReactionId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private long postId;
	
	@Override
	public int hashCode() {
		return Objects.hash(postId, userId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReactionId other = (ReactionId) obj;
		return postId == other.postId && userId == other.userId;
	}
	
}
