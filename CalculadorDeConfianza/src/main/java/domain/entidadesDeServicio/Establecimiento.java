package domain.entidadesDeServicio;

import domain.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "establecimiento")
public class Establecimiento extends EntidadPersistente {

    @Column
    private String nombre;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Servicio> servicios;

    public Establecimiento() {
        this.servicios = new ArrayList<>();
    }

    public void agregarServicios(Servicio ... servicios1) {
        Collections.addAll(this.servicios, servicios1);
    }
}