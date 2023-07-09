package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponce;
import com.app.dto.CourseDto;
import com.app.service.CourseService;



@RestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@PostMapping
	public ResponseEntity<?> addNewCourse(@RequestBody @Valid CourseDto courses){
		try {
			return new ResponseEntity<>(courseService.addCourses(courses),HttpStatus.OK);
			
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ApiResponce(e.getMessage()),HttpStatus.NOT_FOUND);
		}
	} 
	
	@GetMapping
	public ResponseEntity<?> getallCourse(){
		return new ResponseEntity<>(courseService.getCourses(),HttpStatus.OK);
	}
	
	@PutMapping("/{id}/fees/{fee}")
	public ResponseEntity<?> updateFees(@PathVariable Long id,@PathVariable double fee){
		try {
			return new ResponseEntity<>(courseService.updateFeeOfCourses(id,fee),HttpStatus.OK);
			
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ApiResponce(e.getMessage()),HttpStatus.NOT_FOUND);
		}
		
	}
}
