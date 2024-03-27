package com.example.purrpost.model.compositeid;

import java.io.Serializable;
import java.util.Objects;

public class InteractionId implements Serializable {
	private int userId;
	private int postId;
	
	// getters, setters
	// equals and hashCode
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InteractionId other = (InteractionId) obj;
		return postId == other.postId && userId == other.userId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(postId, userId);
	}
}
