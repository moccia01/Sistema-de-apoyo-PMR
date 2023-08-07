package domain.mensajes.Configuraciones;

import domain.mensajes.MailSender;
import domain.comunidad.Miembro;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ServicioMail implements MailSender {
    private final String usuario = "sistemaapoyopmr@gmail.com";
    private final String contrasenia = "pryamqgjzmqvftfg";

    @Override
    public void enviarMensaje(Miembro miembro, String asunto, String notificacion) {
        // Configuración del servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // Crear una sesión de correo
        AutenticadorMail autenticadorMail = new AutenticadorMail(usuario,contrasenia);
        Session session = Session.getInstance(props, autenticadorMail);
        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(miembro.getUsuario().getMail()));
            // Establecer el asunto y el contenido del mensaje
            emailMessage.setSubject(asunto);
            emailMessage.setText(notificacion);
            // Enviar el mensaje
            Transport.send(emailMessage);
            System.out.println("El correo ha sido enviado exitosamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
