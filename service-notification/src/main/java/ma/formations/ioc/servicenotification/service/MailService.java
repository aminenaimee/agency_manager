package ma.formations.ioc.servicenotification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Sends an email using Thymeleaf template.
     *
     * @return Success or failure message
     */
    public String sendMail(Long reservationId, String hotelName, String hotelMail, String roomName, String roomType, LocalDate checkIn, LocalDate checkOut , String departure, LocalDate departureTime , String vehicleName, String vehicleBrand, LocalDate rentStartDate, LocalDate rentEndDate, Long totalAmount) {
        try {
            // Set up Thymeleaf context with variables
            Context context = new Context();
            context.setVariable("header", "Reservation Confirmation Email");
            context.setVariable("title", "Dear Customer,");
            context.setVariable("description", "This is a sample email sent using Thymeleaf template in Spring Boot.");
            context.setVariable("reservationId", reservationId);

            context.setVariable("hotelName", hotelName);
            context.setVariable("hotelMail", hotelMail);
            context.setVariable("roomName", roomName);
            context.setVariable("roomType", roomType);
            context.setVariable("checkIn", checkIn);
            context.setVariable("checkOut", checkOut);
            context.setVariable("departure", departure);
            context.setVariable("departureTime", departureTime);
            context.setVariable("vehicleName", vehicleName);
            context.setVariable("vehicleBrand", vehicleBrand);
            context.setVariable("rentStartDate", rentStartDate);
            context.setVariable("rentEndDate", rentEndDate);
            context.setVariable("totalAmount", totalAmount);




            String body = templateEngine.process("email-template", context);


            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo("Slhddnnaime@gmail.com");
            helper.setSubject("Test Email with Thymeleaf Template");
            helper.setText(body, true);
            helper.setFrom("aminenaime14@example.com");


            javaMailSender.send(mimeMessage);

            return "Email sent successfully to recipient@example.com!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
}