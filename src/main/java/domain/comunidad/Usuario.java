package domain.comunidad;

import domain.localizacion.Localizacion;
import domain.validaciones.CredencialDeAcceso;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Transient
    private CredencialDeAcceso credencialDeAcceso;
    @Transient
    private String mail;
    @Transient
    private String telefono;
    @Transient
    private Interes interes;
    @Transient
    private Localizacion localizacion;

    public void setLocalizacion(String provincia, String departamento, String direccion){
        Localizacion localizacionSet = new Localizacion();
        localizacionSet.setProvincia(provincia);
        localizacionSet.setDireccion(departamento, direccion);
        this.localizacion = localizacionSet;
    }
}
