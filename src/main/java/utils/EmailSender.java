package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    public static void sendReport(String toEmail, String reportPath) {
        final String fromEmail = "poojachilwant31@gmail.com";      // sender email
        final String password = "qeqr brlv yvjz urlp";// app password if Gmail


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");        // SMTP host
        props.put("mail.smtp.port", "587");                   // TLS Port
        props.put("mail.smtp.auth", "true");                  // enable auth
        props.put("mail.smtp.starttls.enable", "true");       // enable STARTTLS

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("API Test Execution Report");
            msg.setSentDate(new Date());

            String emailBody =
                    "Hi Team,<br><br>" +
                            "We have completed test execution for the build. Please find the attached report.<br><br>" +
                            "Thanks and Regards,<br>" +
                            "Pooja Navale";

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailBody, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(reportPath);
            multipart.addBodyPart(attachPart);

            msg.setContent(multipart);

            Transport.send(msg);

            System.out.println("✅ Email sent successfully to: " + toEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
