package mx.com.lickodev.udemy.control.courses.feign.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
