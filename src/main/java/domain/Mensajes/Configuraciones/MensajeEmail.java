package domain.Mensajes.Configuraciones;
import domain.Mensajes.Configuraciones.MedioConfigurado;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class MensajeEmail implements MedioConfigurado {
    private final String usuario = "sistemaapoyopmr@gmail.com";
    private final String contra = "pryamqgjzmqvftfg";
    private final String subtitulo = "Notificacion Comunidad";


        public void enviarNotificacion(Miembro miembro, String notificacion) {
            // Configuración del servidor de correo
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            // Crear una sesión de correo
            AutenticadorMail autenticadorMail = new AutenticadorMail(usuario,contra);
            Session session = Session.getInstance(props, autenticadorMail);
            try {
                Message emailMessage = new MimeMessage(session);
                emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(miembro.getUsuario().getMail()));
                // Establecer el asunto y el contenido del mensaje
                emailMessage.setSubject(subtitulo);
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

