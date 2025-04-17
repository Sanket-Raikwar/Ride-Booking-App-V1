package com.cca.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundExceptionClass extends RuntimeException {

	public NotFoundExceptionClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFoundExceptionClass(String message) {
		super(message);
		log.error(message);

	}

}
