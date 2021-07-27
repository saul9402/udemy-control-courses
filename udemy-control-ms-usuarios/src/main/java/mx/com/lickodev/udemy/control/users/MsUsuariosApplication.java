package mx.com.lickodev.udemy.control.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import mx.com.lickodev.udemy.control.commons.config.repository.RepositoryConfig;

@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration
@Import(RepositoryConfig.class)
@EntityScan(basePackages = { "mx.com.lickodev.udemy.control.commons.entity" })
public class MsUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUsuariosApplication.class, args);
	}

}
