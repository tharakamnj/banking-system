package com.nmm.banking.service.impl;

import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.Map;

;

@Service
@Slf4j
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    private final String senderMail = "test@gmail.com";
    private final String mailSubject = "User Registration Email";

    public void sendEmail(String toEmail, String subject, String body){
        log.info("Start sendEmail method");
        SimpleMailMessage message = new SimpleMailMessage();
        try {

            message.setFrom(senderMail);
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(mailSubject);

            javaMailSender.send(message);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("mail sent successfully..");
    }

    /*public void sendEmailWithTemplate(EmailDTO mail,String mailType) {
        log.info("Start sendEmailWithTemplate method");
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mailSubject);
            mimeMessageHelper.setFrom(senderMail);
            mimeMessageHelper.setTo(mail.getTo());
            if (mailType == "codeSend"){
                mail.setContent(getContentFromTemplate(mail.getModel()));
            } else if (mailType == "resetSuccess") {
                mail.setContent(getContentFromTemplateForResetSuccess(mail.getModel()));
            }

            mimeMessageHelper.setText(mail.getContent(), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("mail sent successfully..");
    }

    public String getContentFromTemplate(Map< String, Object > model)     {
        log.info("Start getContentFromTemplate method");
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("template.flth"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("success..");
        return content.toString();
    }

    public String getContentFromTemplateForResetSuccess(Map< String, Object > model)     {
        log.info("Start getContentFromTemplate method");
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("resetsuccess.flth"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("success..");
        return content.toString();
    }*/
}
