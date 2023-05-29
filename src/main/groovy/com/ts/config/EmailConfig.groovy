package com.ts.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.mail.PasswordAuthentication
import javax.mail.Session

@Configuration
class EmailConfig {

    private String noReplyEmailId = "skillertest20@gmail.com";
    private String noReplyEmailPassword = "uiuuuuozjkasrote";

    @Bean
    Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        // props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        // Establishing a session with required user details
        return Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("noReplyEmailId =======> " + noReplyEmailId);
                System.out.println("noReplyEmailPassword ======> " + noReplyEmailPassword);
                return new PasswordAuthentication(noReplyEmailId, noReplyEmailPassword);
            }
        });
    }
}
