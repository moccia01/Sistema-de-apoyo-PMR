package domain.models.entities.comunidad;

import domain.models.entities.db.EntidadPersistente;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Servicio;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Entity
@Table(name = "interes")
public class Interes extends EntidadPersistente{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Entidad> entidades;

    @ManyToMany(cascade = CascadeType.ALL)
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
