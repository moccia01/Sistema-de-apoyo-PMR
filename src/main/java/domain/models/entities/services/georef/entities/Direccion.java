package domain.models.entities.services.georef.entities;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Getter
@Embeddable
public class Direccion {

    @Column(name = "direccion_nomenclatura")
    public String nomenclatura;     // Formato: "calle altura, departamento, provincia" EJ: "FLORIDA 2950, Merlo, Buenos Aires"

    @Transient
    public Ubicacion ubicacion;
}
