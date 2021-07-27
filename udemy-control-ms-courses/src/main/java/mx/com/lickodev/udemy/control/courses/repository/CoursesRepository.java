package mx.com.lickodev.udemy.control.courses.repository;

import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.validation.annotation.Validated;

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
@Validated
public interface CoursesRepository extends JpaRepository<Course, Long> {

	Set<Course> findByName(@Param("name") String name) throws CourseNotFoundException;

	Page<Course> findAllByNameContaining(@Param("name") String name, Pageable page);

	/**
	 * Para que este metodo funcione de manera efectiva se debe mandar el mismo
	 * valor a los 3 parametros
	 * 
	 * @param name
	 * @param url
	 * @param description
	 * @param page
	 * @return
	 */
	Page<Course> findAllByNameContainingIgnoreCaseOrUrlContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
			@Param("name") String name, @Param("url") String url, @Param("description") String description,
			Pageable page);

	Page<Course> findAllByNameLikeOrUrlLikeOrDescriptionLike(@Param("name") String name, @Param("url") String url,
			@Param("description") String description, Pageable page);

	/**
	 * Se agregan validaciones a los parametros con el API de validacion de Java, se
	 * agrega link donde se puede encontrar una lista de anotaciones posibles a usar
	 * 
	 * https://www.baeldung.com/javax-validation
	 * 
	 * @param name
	 * @return
	 */
	boolean existsByNameContaining(@Size(min = 2, max = 6, message = "error-min-max") @Param("name") String name);

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
