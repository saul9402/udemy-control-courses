package mx.com.lickodev.udemy.control.courses.repository;

import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import mx.com.lickodev.udemy.control.commons.constants.ErrorMessage;
import mx.com.lickodev.udemy.control.commons.constants.MethodPermissions;
import mx.com.lickodev.udemy.control.commons.constants.ParameterNames;
import mx.com.lickodev.udemy.control.commons.constants.Paths;
import mx.com.lickodev.udemy.control.commons.entity.courses.Course;
import mx.com.lickodev.udemy.control.commons.exceptions.CourseNotFoundException;

/**
 * 
 * @author saul_
 *
 *
 *         https://codeboje.de/spring-data-rest-tutorial
 */
@Validated
@RepositoryRestResource(path = Paths.COURSES_BASE_PATH)
public interface CoursesRepository extends JpaRepository<Course, Long> {

	/**
	 * Se agregan validaciones a los parametros con el API de validacion de Java, se
	 * agrega link donde se puede encontrar una lista de anotaciones posibles a usar
	 * 
	 * https://www.baeldung.com/javax-validation
	 * 
	 * @param name
	 * @return
	 */
	boolean existsByNameContaining(
			@Size(min = 2, max = 6, message = ErrorMessage.LABEL_ERROR_URL_MIN_MAX) @Param(ParameterNames.PARAMETER_NAME) String name);

	Page<Course> findAllByNameContaining(@Param(ParameterNames.PARAMETER_NAME) String name, Pageable page);

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
			@Param(ParameterNames.PARAMETER_NAME) String name, @Param(ParameterNames.PARAMETER_URL) String url,
			@Param(ParameterNames.PARAMETER_DESCRIPTION) String description, Pageable page);

	Page<Course> findAllByNameLikeOrUrlLikeOrDescriptionLike(@Param(ParameterNames.PARAMETER_NAME) String name,
			@Param(ParameterNames.PARAMETER_URL) String url,
			@Param(ParameterNames.PARAMETER_DESCRIPTION) String description, Pageable page);

	/**
	 * https://www.baeldung.com/spring-security-method-security
	 * 
	 * @param name
	 * @return
	 * @throws CourseNotFoundException
	 */
	@PreAuthorize(MethodPermissions.HAS_ANY_ROLE_USER_OR_ADMIN)
	Set<Course> findByName(@Param(ParameterNames.PARAMETER_NAME) String name) throws CourseNotFoundException;

	@Override
	@PreAuthorize(MethodPermissions.HAS_ROLE_ADMIN)
	<S extends Course> S save(S entity);

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
