package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponce;
import com.app.dto.CourseDto;
import com.app.dto.StudentDto;
import com.app.dto.updateStudentDto;
import com.app.entity.Course;
import com.app.entity.Student;
import com.app.repository.CourseRepo;
import com.app.repository.StudentRepo;

import io.github.classgraph.InfoList;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
@Autowired
private StudentRepo stuRepo;
@Autowired
private ModelMapper mapper;
@Autowired 
private CourseRepo crepo;
	
@Override
	public String addStudent(StudentDto student) {
		Course cors=crepo.findByTitle(student.getCourseTitle())
				.orElseThrow(()->new ResourceNotFoundException(" invalid Title!!"));
		if(student.getScore()>cors.getMinScore()) {
		Student stud=mapper.map(student, Student.class);
		
		cors.addStudent(stud);
		Student persistantStudent=stuRepo.save(stud);
		return " addmited congrats:: "+persistantStudent.getFirstName()+" you get "+cors.getTitle();
		}
		return " is not get addmission due to low score ";
	}

@Override
public List<StudentDto> getAllStudent() {
	List<Student> stud=stuRepo.findAll();
	return stud.stream().map(student->mapper.map(student, StudentDto.class)).collect(Collectors.toList());
}

@Override
public String deleteStudentDetails(Long studId) {
	String mesg = "Emp id invalid , can't delete emp details ";
	
	if(stuRepo.existsById(studId)) {
		stuRepo.deleteById(studId);
		mesg="student with id:"+studId+" get deleted";
	}
	return mesg;
}

@Override
public List<StudentDto> getAllStudentByTitle(String courseTitle) {
	List<Student> stud=stuRepo.findAllByAssigncourseTitle(courseTitle).orElseThrow(()->new ResourceNotFoundException("No students enrolled in course " + courseTitle));
	return stud.stream().map(student->mapper.map(student, StudentDto.class)).collect(Collectors.toList());
}

@Override
public String updateStudent(updateStudentDto updateStu) {
	if(stuRepo.existsById(updateStu.getId())) {
	Student stu=stuRepo.save(mapper.map(updateStu, Student.class));
	return "student data change sucessfuly "+stu.getFirstName();
	}
	return "student id not match";
}

@Override
public String updateStudentDetail(Long id, int score, String title) {
	Student stu=stuRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("id not found"));
	stu.setScore(score);
	Course c=crepo.findByTitle(title).orElseThrow(()->new ResourceNotFoundException("title not found"));
	stu.setAssigncourse(c);
	return "student details updated";
	
}





}
