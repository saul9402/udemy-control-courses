package mx.com.lickodev.udemy.control.autentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration // (exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
//@Import(RepositoryConfig.class)
@EntityScan(basePackages = { "mx.com.lickodev.udemy.control.commons.entity.*" })
@ComponentScan(basePackages = { "mx.com.lickodev.udemy.control.autentication.*",
		"mx.com.lickodev.udemy.control.autentication.feignclients.*" })
public class MsAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthenticationApplication.class, args);
	}

}
