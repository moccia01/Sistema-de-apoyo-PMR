package domain.services.georef.entities;

import lombok.Getter;

@Getter
public class Direccion {

    public String nomenclatura;     // Formato: "calle altura, departamento, provincia" EJ: "FLORIDA 2950, Merlo, Buenos Aires"

    public Ubicacion ubicacion;
}
