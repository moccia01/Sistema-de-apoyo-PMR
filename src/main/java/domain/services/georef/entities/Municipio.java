package domain.services.georef.entities;

import domain.db.EntidadPersistente;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "municipio")
public class Municipio extends EntidadPersistente {

    @Column
    public String nombre;
}