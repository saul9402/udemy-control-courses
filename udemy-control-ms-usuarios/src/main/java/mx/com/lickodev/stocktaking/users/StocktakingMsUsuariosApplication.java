package mx.com.lickodev.stocktaking.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@EntityScan(basePackages = { "mx.com.lickodev.stocktaking.commons.entity" })
public class StocktakingMsUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocktakingMsUsuariosApplication.class, args);
	}

}
