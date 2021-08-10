package mx.com.lickodev.udemy.control.commons.entity.users;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "userName" }, name = "unique_users_username_constraint") })
@EqualsAndHashCode(callSuper = false, of = { "userName" })
/**
 * https://stackoverflow.com/questions/16564789/changing-the-generated-name-of-a-foreign-key-in-hibernate
 * https://docs.jboss.org/hibernate/orm/5.1/userguide/html_single/chapters/domain/inheritance.html
 */
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_users_persons"), referencedColumnName = "id")
/**
 * @author saul_
 * 
 *         Se cambia definicion de la llave primaria con la que se hace el join
 *         column, se llama antes person_id y se cambio solo por id, esto para
 *         poder utilizar projections en los respositorios ya que de no hacerlo
 *         asi se encontraba una discrepancia entre los nombres de el DTO de
 *         projection, el select y la propia entidad, para evitra dicha
 *         discrepancia de nombres se opta por cambiar el nombre de la columna
 *         de esta tabla (entidad)
 * 
 */
public class User extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 60)
	private String userName;

	/**
	 * https://stackoverflow.com/questions/12638209/reason-cannot-pass-null-or-empty-values-to-constructor-in-spring-security
	 * Se elimina la anotación ya que no permitia validar al usuario en el login
	 * WRITE_ONLY
	 */
	@Column(length = 350)
	private String password;
	/**
	 * https://stackoverflow.com/questions/3110266/how-to-set-a-default-entity-property-value-with-hibernate
	 */
	@Column(nullable = false, columnDefinition = "tinyint default true")
	private boolean enabled;

	@Column(nullable = false, columnDefinition = "tinyint default true")
	private boolean accountNonExpired;

	@Column(nullable = false, columnDefinition = "tinyint default true")
	private boolean credentialsNonExpired;

	@Column(nullable = false, columnDefinition = "tinyint default true")
	private boolean accountNonLocked;

	/**
	 * Al realizar el cambio de nombre de la columna de user se tiene que cambiar
	 * aqui también para poder seguir haciendo la relación.
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") }, uniqueConstraints = {
							@UniqueConstraint(columnNames = { "id",
									"role_id" }, name = "unique_users_roles_constraint") }, foreignKey = @ForeignKey(name = "fk_user_id"), inverseForeignKey = @ForeignKey(name = "fk_role_id"))
	private List<Role> roles;

}
