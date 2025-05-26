package com.javaspringboot.JavaSpringBoot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaspringboot.JavaSpringBoot.dao.CourseDao;
import com.javaspringboot.JavaSpringBoot.entities.Course;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDao courseDao;

	List<Course> list;
	
	public CourseServiceImpl() {
		
	}
	
	@Override
	public List<Course> getCourses() {
		return courseDao.findAll();
	}

	@Override
	public Course getCourse(long courseId) {
		return courseDao.findById(courseId).orElse(null);
	}

	@Override
	public Course addCourse(Course course) {
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
	    courseDao.save(course);
		return course;
	}

	@Override
	public void deleteCourse(long parseLong) {
	      if(!courseDao.existsById(parseLong)) {
	    	  throw new RuntimeException("Course not found with id: " + parseLong);
	      }
	      courseDao.deleteById(parseLong);
	}
	
	

}
