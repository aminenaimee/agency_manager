package ma.formations.ioc.servicenotification;

import jdk.jfr.Enabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableDiscoveryClient
@EnableFeignClients
class ServiceNotificationApplicationTests {

	@Test
	void contextLoads() {
	}

}
