package org.example;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    public static void main(String[] args) {
        final String username = "your_Mail_address"; // replace with your email
        final String password = "your_Password"; // replace with your email password

        List<String> recipients = List.of(
                "example1@gmail.com",
                "example2@gmail.com",
                "example3@gmail.com"

        );

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            for (String recipient : recipients) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                message.setSubject("Test Email");
                message.setText("Hello, this is a test email!. yoyoyo mera phla java project Can't express my happyness");

                Transport.send(message);

                System.out.println("Email sent successfully to " + recipient);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
