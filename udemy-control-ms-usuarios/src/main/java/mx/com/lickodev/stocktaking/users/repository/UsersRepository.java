package mx.com.lickodev.stocktaking.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.com.lickodev.stocktaking.commons.entity.users.User;

@RepositoryRestResource(path = "users")
public interface UsersRepository extends JpaRepository<User, Long> {

	public User findByUserName(@Param("userName") String userName);

}
