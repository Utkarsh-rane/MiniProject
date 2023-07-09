package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Course;
import com.app.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {



	 Optional<List<Student>> findAllByAssigncourseTitle(String courseTitle);

}
