package domain.entidadesDeServicio;

import domain.comunidad.Localizacion;
import lombok.Getter;

import java.util.List;

@Getter
public class Establecimiento {
    private String nombre;
    private Localizacion localizacion;
    private List<Servicio> servicios;

    public void setLocalizacion(String provincia, String departamento, String direccion){
        Localizacion localizacionSet = new Localizacion();
        localizacionSet.setProvincia(provincia);
        localizacionSet.setDireccion(departamento, direccion);
        this.localizacion = localizacionSet;
    }
}
