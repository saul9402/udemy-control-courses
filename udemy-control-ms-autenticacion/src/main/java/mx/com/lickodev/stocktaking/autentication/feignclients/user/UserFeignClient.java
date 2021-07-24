package mx.com.lickodev.stocktaking.autentication.feignclients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.lickodev.stocktaking.commons.entity.users.User;

@FeignClient(name = "stocktaking-ms-users")
public interface UserFeignClient {

	@GetMapping(value = "/users/search/findByUserName")
	public User findByUsername(@RequestParam String userName);

}
