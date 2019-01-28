package am.aca.quiz.software.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {


    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;


    private final TemplateEngine templateEngine;


    public MailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;

        this.templateEngine = templateEngine;
    }

    public void sendText(String emailTo, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

    private void send(String emailTo, String subject,String htmlMsg) {

        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setTo(emailTo);
            helper.setSubject(subject);

            helper.setText(htmlMsg, true);

            mailSender.send(mail);

        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void sendHtml(String to, String subject, String templateName){
        String body = templateEngine.process(templateName,new Context());
        send(to,subject,body);
    }
}
