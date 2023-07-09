package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class updateStudentDto {
	private Long id;
	@NotBlank(message = "name should not be blank")
	private String firstName;
	@NotBlank(message = "last name should not be blank")
	private String lastName;
	@Email(message = "email should be in corect formate")
	private String email;
	private double score;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String courseTitle;
}
