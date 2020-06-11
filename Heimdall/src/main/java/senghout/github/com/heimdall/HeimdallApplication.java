package senghout.github.com.heimdall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HeimdallApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeimdallApplication.class, args);
	}

}
