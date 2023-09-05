package domain.comunidad;

import domain.localizacion.Localizacion;
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
    private String telefono;
    private Interes interes;
    private Localizacion localizacion;

    public void setLocalizacion(String provincia, String departamento, String direccion){
        Localizacion localizacionSet = new Localizacion();
        localizacionSet.setProvincia(provincia);
        localizacionSet.setDireccion(departamento, direccion);
        this.localizacion = localizacionSet;
    }
}
