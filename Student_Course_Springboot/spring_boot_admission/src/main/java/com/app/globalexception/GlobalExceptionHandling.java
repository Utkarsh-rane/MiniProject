package com.app.globalexception;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandling {

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<?> handelMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//		Map<String, String> map = new HashMap<>();
//		for (FieldError f : e.getFieldErrors())
//			map.put(f.getField(), f.getDefaultMessage());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		Map<String, String> map=e.getFieldErrors().stream().collect(Collectors.toMap(f->f.getField(), f->f.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handelResourceNotFoundException(ResourceNotFoundException e) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponce(e.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handalException(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponce(e.getMessage()));
	}
	
}
