package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.CourseDto;
import com.app.entity.Course;
import com.app.repository.CourseRepo;
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
@Autowired
private CourseRepo cRepo;

@Autowired
private ModelMapper mapper;

	@Override
	public String addCourses(CourseDto courses) {
		Course cor=cRepo.save(mapper.map(courses, Course.class));
		return cor.getTitle()+"course added";
	}

	@Override
	public List<CourseDto> getCourses() {
		List<Course> cors=cRepo.findAll();
		return cors.stream().map(course-> mapper.map(course, CourseDto.class)).collect(Collectors.toList());
	}

	@Override
	public String updateFeeOfCourses(Long id, double fee) {
	Course cors=cRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("id not found"));
	cors.setFees(fee);
		return "Updated course fees of " + cors.getTitle() + " to new fees : " + fee;
	}
	

}
