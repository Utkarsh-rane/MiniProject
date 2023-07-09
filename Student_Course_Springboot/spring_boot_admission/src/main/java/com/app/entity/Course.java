package com.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Courses")
public class Course extends BaseEntity {
	@Column(unique = true, length = 20)
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private double fees;
	private double minScore;
	
	@OneToMany(mappedBy = "assigncourse",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Student> stud=new ArrayList<Student>();
	
	public Course(String title, LocalDate startDate, LocalDate endDate, double fees, double minScore) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fees = fees;
		this.minScore = minScore;
	}
	
	public void addStudent(Student student) {
		stud.add(student);
		student.setAssigncourse(this);
	}
	
    private void remove(Student student) {
	stud.remove(student);
	student.setAssigncourse(null);

    }
	
}
