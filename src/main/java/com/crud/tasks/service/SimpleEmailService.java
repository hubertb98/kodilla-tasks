package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SimpleEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail, final boolean toAdmin) {
        log.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail, toAdmin));
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, final boolean toAdmin) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            if (toAdmin) {
                messageHelper.setText(mailCreatorService.buildDailyMail(mail.getMessage()),true);
            } else {
                messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
            }
        };
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
}
