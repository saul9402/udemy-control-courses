package mx.com.lickodev.udemy.control.commons.entity.courses;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mx.com.lickodev.udemy.control.commons.entity.users.Person;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id", "owner" })
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person owner;

	@Column(nullable = false, length = 20)
	private String udemyUserName;

	@Column(nullable = false, length = 200)
	private String udemyPassword;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "accounts_courses", joinColumns = {
			@JoinColumn(name = "account_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "course_id", referencedColumnName = "id") }, uniqueConstraints = {
							@UniqueConstraint(columnNames = { "account_id",
									"course_id" }, name = "unique_accounts_courses_constraint") }, foreignKey = @ForeignKey(name = "fk_account_id"), inverseForeignKey = @ForeignKey(name = "fk_courses_id"))
	private Set<Course> courses = new HashSet<>();

	public void addCourse(Course course) {
		courses.add(course);
		course.getAccounts().add(this);
	}

	public void removeCourse(Course course) {
		courses.remove(course);
		course.getAccounts().remove(this);
	}

}
