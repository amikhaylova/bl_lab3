package business.logic.lab2.service;

import business.logic.lab2.dto.EmailMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(EmailMessageDTO dto){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(dto.getEmail());
        msg.setSubject("Напоминание о бронировании");
        msg.setText("Начало бронирования: " + dto.getBeginningDate());
        mailSender.send(msg);
        System.out.println("Email is sent.");
    }
}
