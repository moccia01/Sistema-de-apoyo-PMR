package domain.models.entities.services.georef.entities;

import domain.models.entities.db.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "departamento")
public class Departamento extends EntidadPersistente{

    @Column
    public String nombre;
}
