package com.app.service;

import java.util.List;

import com.app.dto.StudentDto;
import com.app.dto.updateStudentDto;
import com.app.entity.Student;

public interface StudentService {

	String addStudent(StudentDto student);

	List<StudentDto> getAllStudent();

	String deleteStudentDetails(Long studId);

	List<StudentDto> getAllStudentByTitle(String courseTitle);

	String updateStudent(updateStudentDto updateStu);

	String updateStudentDetail(Long id, int score, String title);


}
