package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	private String title;
	@PastOrPresent(message = "start date should be past or current")
	private LocalDate startDate;
	@Future(message = "end date should be in future")
//	@DateTimeFormat(pattern="dd-MMM-yyyy")
	private LocalDate endDate;
	private double fees;
	private double minScore;
	
}
