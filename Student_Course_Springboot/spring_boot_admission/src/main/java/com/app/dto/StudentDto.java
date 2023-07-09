package com.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.entity.Course;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class StudentDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	@NotBlank(message = "name should not be blank")
	@NotNull(message = "name should not be null")
	private String firstName;
	
	private String lastName;
	@Email(message = "email should not be blank")
	private String email;
	private double score;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String courseTitle;
	
}
