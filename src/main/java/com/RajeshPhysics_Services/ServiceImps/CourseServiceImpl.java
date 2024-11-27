package com.RajeshPhysics_Services.ServiceImps;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.RajeshPhysics_Services.Dtos.CourseDto;
import com.RajeshPhysics_Services.Exceptions.ForbbidonExceptions;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Models.Course;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Repositories.CourseRepository;
import com.RajeshPhysics_Services.Services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        logger.info("Adding a new course: {}", courseDto.getName());
        
        Optional<Course> existingCourse = courseRepo.findByName(courseDto.getName().trim().toUpperCase());
        if (existingCourse.isPresent()) {
            logger.warn("Attempt to add existing course: {}", courseDto.getName());
            throw new ResourceAlreadyExistsException("Course '" + courseDto.getName() + "' already exists.");
        }

        Course course = modelMapper.map(courseDto, Course.class);
        course.setName(courseDto.getName().trim().toUpperCase());
        course.setCourseTitle(courseDto.getCourseTitle().trim());
        course.setCourseLanguage(courseDto.getCourseLanguage().toUpperCase().trim());
        course.setImgPath("default.png");
        course.setCourseDescription(courseDto.getCourseDescription().trim());
        course.setCoursePrice(courseDto.getCoursePrice().trim());

        Course savedCourse = courseRepo.save(course);
        logger.info("Course added successfully: {}", savedCourse.getName());

        return modelMapper.map(savedCourse, CourseDto.class);
    }

    @Override
    public PageableDataResponse<List<Course>> getAllCourse(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search) {
        try {
            Sort sorting = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sorting);
            Page<Course> coursePage;

            if (search == null || search.isEmpty()) {
                coursePage = courseRepo.findAll(pageRequest);
            } else {
                coursePage = courseRepo.findByKeyword(search, pageRequest);
            }

            List<Course> content = coursePage.getContent();
            PageableDataResponse<List<Course>> response = new PageableDataResponse<>();
            response.setContent(content);
            response.setPageNumber(coursePage.getNumber());
            response.setPageSize(coursePage.getSize());
            response.setTotalElements(coursePage.getTotalElements());
            response.setTotalPages(coursePage.getTotalPages());
            response.setLastPage(coursePage.isLast());
            
            logger.info("Fetched {} courses (page {}/{})", content.size(), coursePage.getNumber() + 1, coursePage.getTotalPages());
            return response;

        } catch (Exception e) {
            logger.error("Error occurred while fetching courses: ", e);
            throw new ForbbidonExceptions("Something went wrong while fetching courses!");
        }
    }
}
