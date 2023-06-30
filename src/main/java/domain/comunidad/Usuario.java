package domain.comunidad;

import domain.validaciones.CredencialDeAcceso;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Usuario {
    private String nombre;
    private String apellido;
    private CredencialDeAcceso credencialDeAcceso;
    private String mail;
    private Interes interes;
    private String localizacion;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
