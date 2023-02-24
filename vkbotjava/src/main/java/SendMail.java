import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendMail {
    private static final String from = "javafrom@yandex.ru"; //НЕ ТРОГАТЬ
    private static String to;  // ПОЛУЧАТЕЛЬ
    private static final String password = "cegbkuthamcmvsvm"; //НЕ ТРОГАТЬ

    public static void setTo(String to) {
        SendMail.to = to;
    }

    public static String getTo() {
        return to;
    }

    public SendMail(String code) {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.auth", true);
        props.put("mail.debug", true);

        Session session = Session.getInstance(props);

        try {
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(from));

            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);

            msg.setSubject("Email code check");
            msg.setSentDate(new Date());

            msg.setText(code);

            Transport tr = session.getTransport();
            tr.connect(from, password);
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
