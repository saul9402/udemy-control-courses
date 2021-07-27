package mx.com.lickodev.udemy.control.commons.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import mx.com.lickodev.udemy.control.commons.entity.courses.Course;

/**
 * 
 * @author saul_
 *
 *         https://www.baeldung.com/spring-data-rest-validators;
 *         https://www.baeldung.com/spring-data-rest-intro;
 *         https://stackoverflow.com/questions/24318405/spring-data-rest-validator;
 */
@Component("beforeCreateCourseValidator")
public class CourseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Course.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Course course = (Course) obj;
		if (checkInputString(course.getName())) {
			errors.rejectValue(null, "nombre-vacio", null, "nombre-vacio");
		}

		if (checkInputString(course.getUrl())) {
			errors.rejectValue(null, "url-vacio", null, "url-vacio");
		}
	}

	private boolean checkInputString(String input) {
		return (input == null || input.trim().length() == 0);
	}

}
