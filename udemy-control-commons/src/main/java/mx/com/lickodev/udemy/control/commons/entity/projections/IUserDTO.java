package mx.com.lickodev.udemy.control.commons.entity.projections;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import mx.com.lickodev.udemy.control.commons.entity.users.Role;
import mx.com.lickodev.udemy.control.commons.entity.users.User;

/**
 * 
 * @author saul_
 * 
 *         Con esta es más fácil y transparente mandar los valores de una
 *         determinada lista que se contenga dentro del Objeto padre
 * 
 *         https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.interfaces
 *
 */
@Projection(name = "customProjectionUser", types = User.class)
public interface IUserDTO {

	Long getId();

	String getFirstName();

	String getFirstSurname();

	String getEmail();

	List<Role> getRoles();

	default String getFullName() {
		return getFirstName().concat(" ").concat(getFirstSurname());
	}

}
