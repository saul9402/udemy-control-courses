package mx.com.lickodev.udemy.control.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.com.lickodev.udemy.control.commons.entity.users.User;

/**
 * @author saul_ https://codeboje.de/spring-data-rest-tutorial/
 *
 */
@RepositoryRestResource(path = "users")
public interface UsersRepository extends JpaRepository<User, Long> {

	public User findByUserName(@Param("userName") String userName);

}
