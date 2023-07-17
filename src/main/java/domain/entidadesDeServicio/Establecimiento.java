package domain.entidadesDeServicio;

import domain.comunidad.Localizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Establecimiento {
    private String nombre;
    private Localizacion localizacion;
    private List<Servicio> servicios;

    public void agregarServicios(Servicio ... servicios1){
        Collections.addAll(this.servicios, servicios1);
    }
}
