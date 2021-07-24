package mx.com.lickodev.udemy.control.autentication.service.users;

import mx.com.lickodev.udemy.control.commons.entity.users.User;

public interface UserService {

	public User findByUsername(String userName);

}
