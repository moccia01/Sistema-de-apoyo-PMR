package domain.models.entities.entidadesDeServicio;

import domain.models.entities.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "entidad")
public class Entidad extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String localizacion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entidad_id")
    private List<Establecimiento> listaEstablecimientos;

    @Transient
    private int index;

    public Entidad() {
        this.listaEstablecimientos = new ArrayList<>();
    }

    public void agregarEstablecimientos(Establecimiento ... establecimientos){
        Collections.addAll(this.listaEstablecimientos, establecimientos);
    }

}


