package mx.com.lickodev.udemy.control.commons.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import mx.com.lickodev.udemy.control.commons.constants.CommonUtil;

public class AuthorizationCustomFilter extends BasicAuthenticationFilter {

	private Environment env;

	@Autowired
	public AuthorizationCustomFilter(AuthenticationManager authenticationManager, Environment environment) {
		super(authenticationManager);
		this.env = environment;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String authorizationHeader = request.getHeader(CommonUtil.HEADER_AUTHORIZATION);

		if (authorizationHeader == null || !authorizationHeader.startsWith(CommonUtil.BEARER_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(CommonUtil.HEADER_AUTHORIZATION);

		if (authorizationHeader == null) {
			return null;
		}

		String token = authorizationHeader.replace(CommonUtil.BEARER_PREFIX, "").trim();

		/**
		 * https://www.baeldung.com/java-base64-encode-and-decode;
		 * https://newbedev.com/jwt-signature-does-not-match-locally-computed-signature
		 */
		Jws<Claims> claims = Jwts.parser()
				.setSigningKey(
						Base64.getEncoder().encodeToString(env.getProperty(CommonUtil.JWT_PROPERTY_KEY).getBytes()))
				.parseClaimsJws(token);

		String userName = claims.getBody().get(CommonUtil.JWT_CLAIM_USERNAME).toString();
		/**
		 * https://www.baeldung.com/convert-array-to-list-and-list-to-array
		 */
		List<String> roles = Arrays.asList(claims.getBody().get(CommonUtil.JWT_CLAIM_ROLES).toString().split(","));

		if (userName == null) {
			return null;
		}

		List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(userName, null, authorities);
	}

}
