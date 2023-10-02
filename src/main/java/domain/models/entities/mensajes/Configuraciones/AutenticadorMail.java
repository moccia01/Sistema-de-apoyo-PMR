package domain.models.entities.mensajes.Configuraciones;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AutenticadorMail extends Authenticator{

    private String username;
    private String password;

    public AutenticadorMail(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
