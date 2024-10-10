package com.example.rent_a_car.service.impl;

import com.example.rent_a_car.entity.Car;
import com.example.rent_a_car.service.SendEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${mail.from}")
    private String mailFrom ;


    public void reservationMail(String to ,
                                String costumerName ,
                                String carName ,
                                String brandName ,
                                String gearType ,
                                LocalDate startDate ,
                                LocalDate endDate ,
                                BigDecimal price) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Context context = new Context();
        context.setVariable("costumerName", costumerName);
        context.setVariable("carName", carName);
        context.setVariable("brandName", brandName);
        context.setVariable("gearType", gearType);
        context.setVariable("startDate", startDate );
        context.setVariable("endDate",endDate );
        context.setVariable("price", price);


        String subject = "Rezervasyon bilgileri" ;

        String htmlContent = templateEngine.process("Reservation.html", context);

        helper.setFrom(mailFrom);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);
    }
}
