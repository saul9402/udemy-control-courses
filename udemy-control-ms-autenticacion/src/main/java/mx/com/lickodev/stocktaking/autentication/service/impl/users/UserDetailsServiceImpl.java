package mx.com.lickodev.stocktaking.autentication.service.impl.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserServiceFeignImpl userServiceFeignImpl;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		try {
			mx.com.lickodev.stocktaking.commons.entity.users.User user = userServiceFeignImpl.findByUsername(userName);
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
					.peek(authority -> log.info("Role: {}", authority.getAuthority())).collect(Collectors.toList());

			log.debug("Usuario autenticado: {}", userName);

			return new User(userName, user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
					user.isCredentialsNonExpired(), user.isAccountNonLocked(), authorities);

		} catch (FeignException e) {
			log.error("Error en el login, no existe el usuario '{}' en el sistema", userName);

			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario '" + userName + "' en el sistema");
		}
	}

}
