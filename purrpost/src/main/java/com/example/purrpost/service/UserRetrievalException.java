package com.example.purrpost.service;

public class UserRetrievalException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserRetrievalException() {
        super("Cannot get authentication from SecurityContext");
    }

    public UserRetrievalException(String message) {
        super(message);
    }
}
