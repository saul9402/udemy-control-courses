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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id", "name" })
@Table(name = "courses", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }, name = "unique_name_course_contraint"),
		@UniqueConstraint(columnNames = { "url" }, name = "unique_url_course_contraint") })
//https://www.baeldung.com/spring-data-rest-validators; https://stackoverflow.com/questions/24318405/spring-data-rest-validator - documentacion para agregar validadores usando HATEOAS
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(length = 400)
	private String description;

	@Column(nullable = false, length = 300)
	private String url;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
	private Set<Account> accounts = new HashSet<>();

}
