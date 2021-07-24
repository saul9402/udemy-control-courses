package mx.com.lickodev.udemy.control.autentication.service.impl.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lickodev.udemy.control.autentication.feignclients.user.UserFeignClient;
import mx.com.lickodev.udemy.control.autentication.service.users.UserService;
import mx.com.lickodev.udemy.control.commons.entity.users.User;

@Service
public class UserServiceFeignImpl implements UserService {

	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public User findByUsername(String userName) {
		return userFeignClient.findByUsername(userName);
	}

}
