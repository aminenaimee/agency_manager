package ma.formations.ioc.servicehotel.feign;

import ma.formations.ioc.servicehotel.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(name = "SERVICE-NOTIFICATION")
public interface EmailSender {

    @PostMapping("/mail/send")
    String sendMail(@RequestBody EmailDetails emailDetails);
}
