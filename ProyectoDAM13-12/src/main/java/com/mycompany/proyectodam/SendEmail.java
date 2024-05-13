package com.mycompany.proyectodam;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class SendEmail {
    public static void main(String[] args) {
        // Construir el correo electrónico
        Email email = EmailBuilder.startingBlank()
              //  .from("GymAPP", "thegymapp@outlook.com") // no funciona
                .to("NOMBRE DESTINATARIO", "franquimilazzo@icloud.com") //
                .withSubject("Tu nueva rutina para el gimnasio!!")
                .withPlainText("mensaje aca")
                .buildEmail();

        // Configurar el servidor SMTP
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.office365.com", 587, "thegymapp@outlook.com", "dret3ar$@2q331sadas@4245256") // TMB FORM

                .buildMailer();

        // Enviar el correo electrónico
        mailer.sendMail(email);
        System.out.println("Correo enviado con éxito!");
        
        
        
        
        
        
        
    }
}
