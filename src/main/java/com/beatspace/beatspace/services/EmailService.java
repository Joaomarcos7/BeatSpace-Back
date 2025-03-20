package com.beatspace.beatspace.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // Método para enviar o e-mail
    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setTo(to); // Destinatário
            helper.setSubject(subject); // Assunto
            helper.setText(text); // Corpo do e-mail
            helper.setFrom("jose.marcio@academico.ifpb.edu.br"); // Remetente (seu e-mail)

            // Envia o e-mail
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new MessagingException("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}