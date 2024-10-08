package tn.soft.SchoolMastergo.security.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import tn.soft.SchoolMastergo.security.models.EmailDetails;

import org.springframework.beans.factory.annotation.Value;



import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



import org.springframework.mail.MailException;





@Service
@RequiredArgsConstructor
public class EmailService {




   private final JavaMailSender javaMailSender;
   @Value("${spring.mail.username}")
   private String sender;




   public String sendSimpleMail(EmailDetails emailDetails) {


       try {
           SimpleMailMessage mailMessage = new SimpleMailMessage();
           //Java mail sender takes an argument of type SimpleMailMessage only


           mailMessage.setFrom(sender);
           mailMessage.setTo(emailDetails.getTo());
           mailMessage.setSubject(emailDetails.getSubject());
           mailMessage.setText(emailDetails.getMessageBody());


           javaMailSender.send(mailMessage);
           return "Mail Sent Successfully...";
       }
       catch (MailException e) {
           throw new MailException("erreur") {
           };
       }


   }




   public void sendMail(EmailDetails emailDetails) {


       MimeMessage mimeMessage = javaMailSender.createMimeMessage();
       MimeMessageHelper mimeMessageHelper;


       try {
           mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);


           mimeMessageHelper.setFrom(sender);
           mimeMessageHelper.setTo(emailDetails.getTo());
           mimeMessageHelper.setText(emailDetails.getMessageBody(), true);
           mimeMessageHelper.setSubject(emailDetails.getSubject());


           javaMailSender.send(mimeMessage);


       }
       catch (Exception e) {
           throw new tn.soft.SchoolMastergo.security.exception.MailException(e.getMessage());
       }


   }








}

