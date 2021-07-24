package mx.com.lickodev.stocktaking.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StocktakingEurekaServerApplication {

	public static void main(String[] args) { 
		SpringApplication.run(StocktakingEurekaServerApplication.class, args);
	}

}
