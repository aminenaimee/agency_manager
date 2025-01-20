package ma.formations.ioc.servicedestination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDesinationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDesinationApplication.class, args);
	}

}
