package domain.comunidad;

import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Servicio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Interes {
    private List<Entidad> entidades;
    private List<Servicio> servicios;

    public Interes() {
        this.entidades = new ArrayList<>();
        this.servicios = new ArrayList<>();
    }

    public void agregarEntidades(Entidad ... entidades){
        Collections.addAll(this.entidades, entidades);
    }

    public void agregarServicios(Servicio ... servicios){
        Collections.addAll(this.servicios, servicios);
    }

    public boolean contieneEntidad(Entidad entidad){
        return entidades.contains(entidad);
    }

    public boolean contieneServicio(Servicio servicio){
        return servicios.contains(servicio);
    }
}
