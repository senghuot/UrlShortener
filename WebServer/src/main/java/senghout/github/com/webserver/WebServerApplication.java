package senghout.github.com.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

// Bean is for dependency injections, this is the config file
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
@EnableCaching
public class WebServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebAtomizerService atomizerService() {
		return new WebAtomizerService();
	}

	@Bean
	// injects the dependencies we want into the WebServerController constructor
	public WebServerController webServerController() {
		return new WebServerController(atomizerService());
	}
}
