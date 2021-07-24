package mx.com.lickodev.stocktaking.autentication.service.users;

import mx.com.lickodev.stocktaking.commons.entity.users.User;

public interface UserService {

	public User findByUsername(String userName);

}
