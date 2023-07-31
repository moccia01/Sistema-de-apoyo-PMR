package domain.comunidad;

import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Interes {
    private List<Entidad> entidades;
    private List<Servicio> servicios;

    public boolean contieneEntidad(Entidad entidad){
        return entidades.contains(entidad);
    }

    public boolean contieneServicio(Servicio servicio){
        return servicios.contains(servicio);
    }
}
