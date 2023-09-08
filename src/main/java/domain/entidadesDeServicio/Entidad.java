package domain.entidadesDeServicio;

import domain.db.EntidadPersistente;
import domain.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

@Getter
@Setter
@Entity
@Table(name = "entidad")
public class Entidad extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;

    @Transient
    private String localizacion;

    @OneToMany
    @JoinColumn(name = "entidad_id")
    private List<Establecimiento> listaEstablecimientos;

    public Entidad() {
        this.listaEstablecimientos = new ArrayList<>();
    }

    public void agregarEstablecimientos(Establecimiento ... establecimientos){
        Collections.addAll(this.listaEstablecimientos, establecimientos);
    }

}


