package mx.com.lickodev.udemy.control.commons.entity.projections;


import lombok.Value;

/**
 * 
 * @author saul_
 *
 *
 *         https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.dtos
 */
@Value
public class UserProjectionDTO {

	private Long id;

	private String firstName;

	private String secondName;

	private String firstSurname;

	private String secondSurname;

	private String email;

	private String userName;

	private boolean enabled;

	private boolean accountNonExpired;

	private boolean credentialsNonExpired;

	private boolean accountNonLocked;

	/**
	 * Según la propia documentación de spring, bajo este esquema o comportamiento no
	 * es posible hacer projection de propiedades anidadas.
	 * 
	 * Agosto 8 del 2021.
	 * 
	 * "These DTO types can be used in exactly the same way projection interfaces
	 * are used, except that no proxying happens and no nested projections can be
	 * applied."
	 */
//	private List<RoleProjectionDTO> roles;

}
