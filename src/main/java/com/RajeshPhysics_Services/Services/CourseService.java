package com.RajeshPhysics_Services.Services;

import java.util.List;

import com.RajeshPhysics_Services.Dtos.CourseDto;
import com.RajeshPhysics_Services.Models.Course;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;

public interface CourseService {
	public CourseDto addCourse(CourseDto courseDto);
	public PageableDataResponse<List<Course>> getAllCourse(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);

}
