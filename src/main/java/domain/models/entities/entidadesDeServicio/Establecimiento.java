package domain.models.entities.entidadesDeServicio;

import domain.models.entities.db.EntidadPersistente;
import domain.models.entities.localizacion.Localizacion;
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

    @Column(name="nombre")
    private String nombre;

    @Embedded
    private Localizacion localizacion;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Servicio> servicios;

    public Establecimiento() {
        this.servicios = new ArrayList<>();
    }

    public void agregarServicios(Servicio ... servicios1) {
        Collections.addAll(this.servicios, servicios1);
    }
    public void setLocalizacion(String provincia, String departamento, String direccion){
        Localizacion localizacionSet = new Localizacion();
        localizacionSet.setProvincia(provincia);
        localizacionSet.setDireccion(departamento, direccion);
        this.localizacion = localizacionSet;
    }
}
