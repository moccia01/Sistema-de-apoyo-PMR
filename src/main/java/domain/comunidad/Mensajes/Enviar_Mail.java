package domain.comunidad.Mensajes;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class Enviar_Mail {

        public void enviar_Mail(Usuario usuario, String mensaje) {
            // Datos de la cuenta de correo
            final String username = "";
            final String password = "";
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
                emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getMail()));

                // Establecer el asunto y el contenido del mensaje
                emailMessage.setSubject(subtitulo);
                emailMessage.setText(mensaje);

                // Enviar el mensaje
                Transport.send(emailMessage);

                System.out.println("El correo ha sido enviado exitosamente.");
            } catch (MessagingException e) {
                System.out.println("Error al enviar el correo: " + e.getMessage());
            }
        }
}

