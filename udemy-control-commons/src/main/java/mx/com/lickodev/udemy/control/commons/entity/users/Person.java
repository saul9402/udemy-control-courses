package mx.com.lickodev.udemy.control.commons.entity.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
/**
 * Los columnNames harán referencia a la propiedad en Java no en la BD
 * https://stackoverflow.com/questions/32160370/hibernate-constraint-name
 */
@Table(name = "persons", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "email" }, name = "unique_persons_email_constraint") })
@EqualsAndHashCode(of = { "id", "email" })
/**
 * https://www.dineshonjava.com/hibernate/implementing-inheritance-in-hibernate/
 */
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 60)
	private String firstName;

	@Column(length = 60)
	private String secondName;

	@Column(length = 60)
	private String firstSurname;

	@Column(length = 60)
	private String secondSurname;

	@Column(length = 560, nullable = false)
	private String email;

}
