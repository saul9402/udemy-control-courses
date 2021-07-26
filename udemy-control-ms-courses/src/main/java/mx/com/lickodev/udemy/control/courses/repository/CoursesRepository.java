package mx.com.lickodev.udemy.control.courses.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.com.lickodev.udemy.control.commons.entity.courses.Course;
import mx.com.lickodev.udemy.control.commons.exceptions.CourseNotFoundException;

/**
 * 
 * @author saul_
 *
 *
 *         https://codeboje.de/spring-data-rest-tutorial
 */
@RepositoryRestResource(path = "courses")
public interface CoursesRepository extends JpaRepository<Course, Long> {

	Set<Course> findByName(@Param("name") String name) throws CourseNotFoundException;

	Page<Course> findAllByNameContaining(@Param("name") String name, Pageable page);

	boolean existsByNameContaining(@Param("name") String name);

}
