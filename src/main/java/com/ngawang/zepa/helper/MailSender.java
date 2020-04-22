package com.ngawang.zepa.helper;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class MailSender {

    public static Boolean sendMail(String destinationEmail, String sentMailFrom, File attachmentFile, String messageBody,
                                   String subject) throws Exception {

        boolean isMailSent = false;
        final String trayMessage;
        Resource resource = new ClassPathResource("/mailConfig/mailConfig.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);

        final String isMailSendRequired = props.getProperty("mail.isRequiredToSend");

        if (isMailSendRequired.equalsIgnoreCase("0") || isMailSendRequired.isEmpty()) {
            return isMailSent;
        }
        sentMailFrom = props.getProperty("mail.fromAddress", "");

        if (sentMailFrom.isEmpty()) {
            return isMailSent;
        }

        if (destinationEmail == null || destinationEmail.isEmpty()) {
            new Thread() {
                public void run() {
                    String errorMsg = "Mail cannot be sent. There is no mail address given to sent mail";
                    SystemTrayIcon.displayTray(errorMsg);
                }
            }.start();
            return isMailSent;
        }

        final String username = props.getProperty("mail.username", "");
        final String password = props.getProperty("mail.password", "");
        String host = props.getProperty("mail.host", "mail01");
        String port = props.getProperty("mail.port", "25");
        String auth = props.getProperty("mail.auth", "true");
        String startLlsEnable = props.getProperty("mail.startLlsEnable", "true");

        //smtp configuration
        String smtpHost = props.getProperty("mail.smtpHost", "mail.smtp.host");
        String smtpPort = props.getProperty("mail.smtpPort", "mail.smtp.port");
        String smtpAuth = props.getProperty("mail.smtpAuth", "mail.smtp.auth");
        String smtpStartLlsEnable = props.getProperty("mail.smtpStartLlsEnable", "mail.smtp.starttls.enable");

        Properties properties = new Properties();
        properties.put(smtpHost, host);
        properties.put(smtpPort, port);
        properties.put(smtpAuth, auth);
        properties.put(smtpStartLlsEnable, startLlsEnable);

        //creating session of current user.
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sentMailFrom));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationEmail));
        message.setSubject(subject);
        message.setSentDate(new Date());

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(messageBody);

        Multipart multipart = new MimeMultipart();
        //set text part message
        multipart.addBodyPart(messageBodyPart);

        //adding attachmentFile
        if (attachmentFile != null) {
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.attachFile(attachmentFile);
            multipart.addBodyPart(messageBodyPart1);
        }

        //send the complete message parts
        message.setContent(multipart);
        trayMessage = "Mail is sent successfully to " + destinationEmail;
        new Thread() {
            public void run() {
                try {
                    Transport.send(message);
                    SystemTrayIcon.displayTray(trayMessage);
                } catch (MessagingException e) {
                    String errorMsg = "Mail cannot be sent. Check your net connection or configuration or " +
                            "if destination email address is valid or not.";
                    SystemTrayIcon.displayTray(errorMsg);
                }
            }
        }.start();
        isMailSent = true;
        return isMailSent;

    }

    public static Boolean sendMailWithoutFile(String message, String subject, String toAddress, String fromAddress) throws Exception {

        Boolean isMailSent = MailSender.sendMail(toAddress, fromAddress, null, message, subject);
        return isMailSent;
    }

    public static Boolean sendMailWithFile(MultipartFile multipartFile, String message, String toAddress) throws Exception {
        String fromAddress = "info@bcsea.gov.bt";
        //String message1 = "User creation success";
        String subject = "User creation";

        File file = convertToFile(multipartFile);

        Boolean isMailSent = MailSender.sendMail(toAddress, fromAddress, file, message, subject);
        return isMailSent;
    }

    public static File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }
}