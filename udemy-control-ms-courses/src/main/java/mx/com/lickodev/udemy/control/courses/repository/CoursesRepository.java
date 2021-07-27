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

	Page<Course> findAllByNameContainingOrUrlContainingOrDescriptionContaining(@Param("name") String name,
			@Param("url") String url, @Param("description") String description, Pageable page);

	boolean existsByNameContaining(@Param("name") String name);	

	/**
	 * 
	 * Se puede "ocultar" métodos predefinidos por el API de JPA de esta forma para
	 * evitar ataques maliciosos.
	 * 
	 * https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.hiding-repository-crud-methods
	 * 
	 * @Override
	 * @RestResource(exported = false) void delete(Course entity);
	 * 
	 * @Override
	 * @RestResource(exported = false) void deleteById(Long id);
	 */

}
