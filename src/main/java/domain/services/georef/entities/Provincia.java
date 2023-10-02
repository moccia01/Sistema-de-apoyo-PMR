package domain.services.georef.entities;

import domain.db.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "provincia")
public class Provincia extends EntidadPersistente {
    @Column
    public String nombre;
}