package mx.com.lickodev.udemy.control.courses.feign.clients;

import mx.com.lickodev.udemy.control.commons.entity.users.User;

public interface UserService {

	User findByUsername(String userName);

}
