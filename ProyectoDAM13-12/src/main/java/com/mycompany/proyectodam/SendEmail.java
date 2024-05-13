package com.mycompany.proyectodam;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class SendEmail {
    public static void main(String[] args) {
        // Construir el correo electrónico
        Email email = EmailBuilder.startingBlank()
                .from("GymAPP", "thegymapp@outlook.com") // ACA FROM
                .to("Franqui Milazzo", "franquimilazzo@icloud.com") //
                .withSubject("Tu nueva rutina para el gimnasio!!")
                .withPlainText("Hola Franqui,\n\nAquí tienes tu nueva rutina de gimnasio:\n\n1. Calentamiento: 10 minutos de cardio ligero.\n2. Pesas: 3 series de 10 repeticiones de press de banca.\n3. Cardio: 20 minutos de bicicleta estática.\n4. Estiramiento: 10 minutos de estiramientos.\n\n¡Espero que te resulte útil!\n\nSaludos,\nTu nombre")
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