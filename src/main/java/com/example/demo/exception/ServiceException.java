package com.example.demo.exception;

public class ServiceException extends RuntimeException {

	private String message;

	public ServiceException(String message) {
		super(message);
		this.message = message;
	}
}
