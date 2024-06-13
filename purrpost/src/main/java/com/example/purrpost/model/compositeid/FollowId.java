package com.example.purrpost.model.compositeid;

import java.io.Serializable;
import java.util.Objects;

public class FollowId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private long followerId;
	
	@Override
	public int hashCode() {
		return Objects.hash(followerId, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FollowId other = (FollowId) obj;
		return followerId == other.followerId && userId == other.userId;
	}
}
