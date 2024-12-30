package ma.formations.ioc.servicenotification.controller;

import ma.formations.ioc.servicenotification.EmailDetails;
import ma.formations.ioc.servicenotification.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;
    @PostMapping("/send")
    public String sendMail(@RequestBody EmailDetails emailDetails) {

        return mailService.sendMail( emailDetails.getReservationId(), emailDetails.getHotelId(), emailDetails.getHotelName(), emailDetails.getHotelMail(), emailDetails.getRoomName(), emailDetails.getRoomType(), emailDetails.getCheckIn(), emailDetails.getCheckOut(), emailDetails.getDeparture(), emailDetails.getDepartureTime());
    }


}
