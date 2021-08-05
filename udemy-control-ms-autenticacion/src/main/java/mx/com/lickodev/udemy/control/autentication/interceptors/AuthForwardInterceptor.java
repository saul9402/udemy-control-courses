package mx.com.lickodev.udemy.control.autentication.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class AuthForwardInterceptor implements RequestInterceptor {

	/**
	 * https://www.programmersought.com/article/63186236135/
	 * https://stackoverflow.com/questions/66983196/get-request-url-in-feign-client-interceptor
	 * https://www.javacodemonk.com/feign-requestinterceptor-in-spring-boot-cbe5d967
	 */
	@Override
	public void apply(RequestTemplate template) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		template.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));

	}

}
