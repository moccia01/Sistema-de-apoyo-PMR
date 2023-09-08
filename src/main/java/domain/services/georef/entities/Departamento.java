package domain.services.georef.entities;

import domain.db.EntidadPersistente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "departamento")
public class Departamento extends EntidadPersistente{

    @Column
    public String nombre;
}
