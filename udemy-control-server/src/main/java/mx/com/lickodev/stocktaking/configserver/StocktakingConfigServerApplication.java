package mx.com.lickodev.stocktaking.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class StocktakingConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocktakingConfigServerApplication.class, args);
	}

}
