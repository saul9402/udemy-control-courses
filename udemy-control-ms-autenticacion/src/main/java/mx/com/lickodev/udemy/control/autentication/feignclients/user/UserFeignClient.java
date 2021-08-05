package mx.com.lickodev.udemy.control.autentication.feignclients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.lickodev.udemy.control.autentication.interceptors.AuthForwardInterceptor;
import mx.com.lickodev.udemy.control.commons.entity.users.User;

@FeignClient(name = "udemy-control-ms-users", configuration = AuthForwardInterceptor.class)
public interface UserFeignClient {

	@GetMapping(value = "/users/search/findByUserName")
	public User findByUsername(@RequestParam String userName);

}
