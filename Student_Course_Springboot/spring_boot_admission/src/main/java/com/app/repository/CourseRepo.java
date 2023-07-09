package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
Optional<Course> findByTitle(String courseTitle);
}
