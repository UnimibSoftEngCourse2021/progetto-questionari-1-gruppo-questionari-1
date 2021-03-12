package webapp.services;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class emailSender{

   
   
   // Sender's email ID needs to be mentioned
   private final String from = "survey.io.project@gmail.com";

   // Assuming you are sending email from localhost
   private final String host = "smtp.gmail.com";

   // Get system properties object
   

   private final String mittente = "survey.io.project@gmail.com";
   private final String password = "Quest:1234";

   

   


    public void sendEmail(String to, String titolo, String codiceCompilazione){
   // Get the default Session object.
   Properties properties = System.getProperties();
    // Setup mail server
    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.user", mittente );
    properties.setProperty("mail.smtp.password", password);
    properties.setProperty("mail.smtp.starttls.enable", "true");
    properties.setProperty("mail.smtp.auth", "true");    
    
   Session mailSession = Session.getDefaultInstance(properties);

   try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(mailSession);
      
      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));
      
      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO,
                               new InternetAddress(to));
      // Set Subject: header field
      message.setSubject("Your survey has been completed by one person!");
      
      // Now set the actual message
      message.setText("il tuo questionario' "+titolo+" ' è stato compilato da una persona! il codice della compilazione è: "+codiceCompilazione);
      
      // Send message
      Transport.send(message,mittente,password);
   
   } catch (MessagingException mex) {
      mex.printStackTrace();
     
   }



    }


}