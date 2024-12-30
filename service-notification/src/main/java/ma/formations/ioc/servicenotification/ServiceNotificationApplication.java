package ma.formations.ioc.servicenotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableDiscoveryClient

public class ServiceNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceNotificationApplication.class, args);

	}


}
