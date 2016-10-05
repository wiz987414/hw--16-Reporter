package ru.sbt.bit.ood.solid.homework.reportSender;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class ReportSender {
    private final JavaMailSenderImpl mailSender;

    public ReportSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
        this.mailSender.setHost("mail.google.com");
    }

    private JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public boolean sendReport(String reportHtmlString, String recipients) {
        MimeMessage message = getMailSender().createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            helper.setText(reportHtmlString, true);
            helper.setSubject("Monthly department salary report");
            getMailSender().send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
