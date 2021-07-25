package mx.com.lickodev.udemy.control.autentication.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import mx.com.lickodev.udemy.control.autentication.service.impl.users.UserServiceFeignImpl;
import mx.com.lickodev.udemy.control.commons.entity.users.User;

@Service
public class TokenEnhacerServiceImpl implements TokenEnhancer {

	@Autowired
	private UserServiceFeignImpl userServiceFeignImpl;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> aditionalTokenInfo = new HashMap<>();
		User usuario = userServiceFeignImpl.findByUsername(authentication.getName());
		aditionalTokenInfo.put("firstName", usuario.getFirstName());
		aditionalTokenInfo.put("firstSurname", usuario.getFirstSurname());
		aditionalTokenInfo.put("secondSurname", usuario.getSecondSurname());
		aditionalTokenInfo.put("email", usuario.getEmail());
		//aditionalTokenInfo.put("roles", usuario.getRoles());
		DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
		defaultOAuth2AccessToken.setAdditionalInformation(aditionalTokenInfo);
		return defaultOAuth2AccessToken;
	}

}
