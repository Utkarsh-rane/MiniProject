package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.dto.CourseDto;

public interface CourseService {

	String addCourses(CourseDto courses);

	List<CourseDto> getCourses();

	String updateFeeOfCourses(Long id, double fee);

	String deletecourse(Long cid);

}
