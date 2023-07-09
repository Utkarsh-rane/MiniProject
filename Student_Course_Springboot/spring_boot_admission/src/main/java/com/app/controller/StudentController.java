package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponce;
import com.app.dto.CourseDto;
import com.app.dto.StudentDto;
import com.app.dto.updateStudentDto;
import com.app.entity.Course;
import com.app.service.StudentService;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {
@Autowired
private StudentService stuService;
	
public StudentController() {
		System.out.println("in student controller cunst ::"+getClass());
	}
	
	@PostMapping
	public ResponseEntity<?> addNewStudent(@RequestBody @Valid StudentDto student){
	
			return new ResponseEntity<>(stuService.addStudent(student),HttpStatus.OK);
		
	}
	@GetMapping
	public ResponseEntity<?> getAllStudents(){
		try {
			System.out.println("in get mapping");
			return new ResponseEntity<>(stuService.getAllStudent(),HttpStatus.OK) ;
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponce(e.getMessage()));
		}
		
	}
	@DeleteMapping("/{studId}")
	public ApiResponce deleteStudentbyId(@PathVariable Long studId) {
		return new ApiResponce(stuService.deleteStudentDetails(studId));
		
	}
	
	@GetMapping("/course_title/{courseTitle}")
	public ResponseEntity<?> getAllStudentsByTitle(@PathVariable String courseTitle ){
		try {
			System.out.println("in get mapping");
			return new ResponseEntity<>(stuService.getAllStudentByTitle(courseTitle),HttpStatus.OK) ;
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponce(e.getMessage()));
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateStudent(@RequestBody updateStudentDto updateStu){
		return new ResponseEntity<>(stuService.updateStudent(updateStu),HttpStatus.OK);
	}
	
	@PutMapping("/{id}/score/{score}/courseTitle/{title}")
	public ResponseEntity<?> updateStudentDetails(@PathVariable Long id, int score, String title){
		return new ResponseEntity<>(stuService.updateStudentDetail(id,score,title),HttpStatus.OK);
	}

}
