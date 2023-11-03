package domain.models.entities.services.georef.entities;

import domain.models.entities.db.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "provincia")
public class Provincia extends EntidadPersistente {
    @Column
    public String nombre;
}