package ma.formations.ioc.servicehotel;

import ma.formations.ioc.servicehotel.entity.Hotel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceHotelApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServiceHotelApplication.class, args);


	}

}
