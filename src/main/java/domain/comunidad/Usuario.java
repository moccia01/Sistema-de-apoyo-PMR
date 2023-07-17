package domain.comunidad;

import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.Direccion;
import domain.services.georef.entities.Provincia;
import domain.validaciones.CredencialDeAcceso;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Setter
@Getter
public class Usuario {
    private String nombre;
    private String apellido;
    private CredencialDeAcceso credencialDeAcceso;
    private String mail;
    private String numero;
    private Interes interes;
    private Localizacion localizacion;

    public void setLocalizacion(String provincia, String departamento, String direccion){
        Localizacion localizacionSet = new Localizacion();
        localizacionSet.setProvincia(provincia);
        localizacionSet.setDireccion(departamento, direccion);
        this.localizacion = localizacionSet;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
