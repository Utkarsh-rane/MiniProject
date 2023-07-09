package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter 
@Setter
@ToString
@Entity
@Table(name = "Students")
public class Student extends BaseEntity {
	@Column(length=30)
	private String firstName;
	@Column(length=30)
	private String lastName;
	@Column(unique = true, length=30)
	private String email;
	private double score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	private Course assigncourse;
	
	
	public Student(String firstName, String lastName, String email, double score) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.score = score;
	}

	
	
}
