package mx.com.lickodev.stocktaking.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class StocktakingZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocktakingZuulServerApplication.class, args);
	}

}
