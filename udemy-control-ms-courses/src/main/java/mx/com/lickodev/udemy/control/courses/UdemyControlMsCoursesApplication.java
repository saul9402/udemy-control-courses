package mx.com.lickodev.udemy.control.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@EntityScan(basePackages = { "mx.com.lickodev.udemy.control.commons.entity" })
public class UdemyControlMsCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyControlMsCoursesApplication.class, args);
	}

}
