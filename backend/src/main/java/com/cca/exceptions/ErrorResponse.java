package com.cca.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
	private String errMessage;
	private LocalDateTime time;
	private String uri;

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ErrorResponse(String errMessage, LocalDateTime time, String uri) {
		super();
		this.errMessage = errMessage;
		this.time = time;
		this.uri = uri;
	}

}
