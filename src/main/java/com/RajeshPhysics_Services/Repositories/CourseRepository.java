package com.RajeshPhysics_Services.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RajeshPhysics_Services.Models.Course;
import com.RajeshPhysics_Services.Models.Role;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findByName(String name);

}
