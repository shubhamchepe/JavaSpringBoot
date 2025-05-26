package com.javaspringboot.JavaSpringBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaspringboot.JavaSpringBoot.entities.Course;

public interface CourseDao extends JpaRepository<Course,Long> {
	
}
