package mx.com.lickodev.udemy.control.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import mx.com.lickodev.udemy.control.commons.config.repository.RepositoryConfig;

/**
 * 
 * @author saul_
 *
 *         https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html
 *
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration
@Import({ RepositoryConfig.class })
@EntityScan(basePackages = { "mx.com.lickodev.udemy.control.commons.entity" })
@ComponentScan(basePackages = { "mx.com.lickodev.udemy.control.commons.*", "mx.com.lickodev.udemy.control.courses.*" })
public class UdemyControlMsCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyControlMsCoursesApplication.class, args);
	}

}
