package com.cca.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrHandler extends RuntimeException {

	@ExceptionHandler(NotFoundExceptionClass.class)
	public ResponseEntity<ErrorResponse> riderNotFoundHandler(NotFoundExceptionClass ex, HttpServletRequest request) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(), LocalDateTime.now(), request.getRequestURI()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ExisitingRideSeekerException.class)
	public ResponseEntity<ErrorResponse> exisitingRideSeekerExHandler(ExisitingRideSeekerException ex,
			HttpServletRequest request) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(), LocalDateTime.now(), request.getRequestURI()), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UnRegistrationException.class)
	public ResponseEntity<ErrorResponse> exisitingRideSeekerExHandler(UnRegistrationException ex,
			HttpServletRequest request) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(), LocalDateTime.now(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BookRideException.class)
	public ResponseEntity<ErrorResponse> bookedSeatNumberExHandler(BookRideException ex,
			HttpServletRequest request) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(), LocalDateTime.now(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(TripAlreadyStartedException.class)
	public ResponseEntity<ErrorResponse> tripAlreadyStartedExHandler(TripAlreadyStartedException ex,
			HttpServletRequest request) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(), LocalDateTime.now(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		BindingResult result = ex.getBindingResult();
		List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(errorMessages.toString(), LocalDateTime.now(), request.getRequestURI()),
				HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleSql(SQLIntegrityConstraintViolationException ex,
			HttpServletRequest request) {
				System.out.println(ex);
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getLocalizedMessage(), LocalDateTime.now(), request.getRequestURI()),
				HttpStatus.NOT_FOUND);
	}

}
