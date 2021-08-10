package mx.com.lickodev.udemy.control.autentication.service.impl.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lickodev.udemy.control.autentication.repository.user.UsersRepository;
import mx.com.lickodev.udemy.control.autentication.service.users.UserService;
import mx.com.lickodev.udemy.control.commons.entity.users.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public User findByUsername(String userName) {
		return usersRepository.findByUserName(userName);
	}

}
