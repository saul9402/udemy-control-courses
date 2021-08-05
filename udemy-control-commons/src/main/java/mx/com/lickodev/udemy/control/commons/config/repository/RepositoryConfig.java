package mx.com.lickodev.udemy.control.commons.config.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import mx.com.lickodev.udemy.control.commons.entity.courses.Course;
import mx.com.lickodev.udemy.control.commons.entity.users.Person;
import mx.com.lickodev.udemy.control.commons.entity.users.User;
import mx.com.lickodev.udemy.control.commons.validators.CourseValidator;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(User.class, Course.class, Person.class);
		/**
		 * Si quieres ocultar algunos metodos del repository para evitar que actualicen,
		 * borren o agreguen info si tú no lo deseas.
		 * 
		 * https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.http-methods.default-exposure
		 */
		config.getExposureConfiguration().forDomainType(Course.class)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.DELETE));
	}

	/**
	 * La documentación indica que con el hecho de declarar el @Component con el
	 * prefijo del nombre del evento es suficiente, pero al parecer existe un bug
	 * que no permite que esto sea correcto, es por ello que se realiza de manera
	 * "manual"
	 * 
	 * https://docs.spring.io/spring-data/rest/docs/current/reference/html/#validation
	 */
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		validatingListener.addValidator("beforeCreate", new CourseValidator());
	}

}
