package mx.com.lickodev.udemy.control.commons.entity.courses;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mx.com.lickodev.udemy.control.commons.constants.ErrorMessage;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id", "name" })
@Table(name = "courses", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }, name = "unique_name_course_contraint"),
		@UniqueConstraint(columnNames = { "url" }, name = "unique_url_course_contraint") })
/**
 * @author saul_
 *
 *
 *         https://www.baeldung.com/spring-data-rest-validators;
 *         https://stackoverflow.com/questions/24318405/spring-data-rest-validator
 */
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	@Size(min = 3, max = 150, message = ErrorMessage.LABEL_ERROR_NAME_MIN_MAX)
	private String name;

	/**
	 * @RestResource(exported = false)
	 * 
	 *                        Al no funcionar la anotacion de arriba para
	 *                        propiedades de las entidades se utiliza el
	 *                        comportamiento del siguiente enlace para ocultar
	 *                        determinadas propiedades (contraseñas por ejemplo)
	 * 
	 *                        https://stackoverflow.com/questions/12505141/only-using-jsonignore-during-serialization-but-not-deserialization;
	 *                        https://docs.spring.io/spring-data/rest/docs/current/reference/html/#representations.serializers-and-deserializers.abstract-classes
	 *                        -> Posible uso para serializar o deserializar algunas
	 *                        propiedades como fechas que a veces generan problemas.
	 * @JsonProperty(access = Access.WRITE_ONLY)
	 */
	@Column(length = 400)
	@Size(min = 20, max = 400, message = ErrorMessage.LABEL_ERROR_DESCRIPTION_MIN_MAX)
	private String description;

	@Column(nullable = false, length = 300)
	@Size(min = 10, max = 300, message = ErrorMessage.LABEL_ERROR_URL_MIN_MAX)
	private String url;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
	private Set<Account> accounts = new HashSet<>();

	/**
	 * @CreatedBy private User user;
	 * 
	 *            https://docs.spring.io/spring-data/jpa/docs/1.7.0.RELEASE/reference/html/#
	 *            auditing
	 */
}
