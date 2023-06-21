package domain.comunidad;

import domain.validaciones.CredencialDeAcceso;

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
