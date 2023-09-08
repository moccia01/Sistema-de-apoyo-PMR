package domain.comunidad;

import domain.db.EntidadPersistente;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Servicio;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Entity
@Table(name = "interes")
public class Interes extends EntidadPersistente{

    @ManyToMany
    private List<Entidad> entidades;

    @ManyToMany
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
