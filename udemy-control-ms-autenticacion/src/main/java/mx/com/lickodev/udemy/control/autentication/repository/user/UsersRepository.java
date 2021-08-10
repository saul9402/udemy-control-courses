package mx.com.lickodev.udemy.control.autentication.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.lickodev.udemy.control.commons.entity.users.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);

}
