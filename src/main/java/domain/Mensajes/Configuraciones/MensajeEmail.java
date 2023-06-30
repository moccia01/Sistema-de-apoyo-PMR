package domain.Mensajes.Configuraciones;
import domain.Mensajes.Configuraciones.MedioConfigurado;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class MensajeEmail implements MedioConfigurado {

        public void enviarNotificacion(Miembro miembro, String notificacion) {
            // Datos de la cuenta de correo encargada de enviar el mail
            final String username = "sistemaapoyopmr@gmail.com";
            final String password = "MovilidadPMR2023";
            final String subtitulo = "Notificacion Comunidad";

            // Configuración del servidor de correo
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Crear una sesión de correo
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Crear el objeto de mensaje
                Message emailMessage = new MimeMessage(session);

                // Establecer los destinatarios
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

