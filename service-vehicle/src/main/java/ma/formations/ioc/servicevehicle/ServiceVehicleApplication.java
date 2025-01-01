package ma.formations.ioc.servicevehicle;

import ma.formations.ioc.servicevehicle.entity.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class ServiceVehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceVehicleApplication.class, args);

	}

}