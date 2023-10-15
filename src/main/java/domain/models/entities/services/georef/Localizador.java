package domain.models.entities.services.georef;

import domain.models.entities.services.georef.entities.Direccion;
import domain.models.entities.services.georef.entities.Departamento;
import domain.models.entities.services.georef.entities.Municipio;
import domain.models.entities.services.georef.entities.Provincia;

import java.io.IOException;

public interface Localizador {

    Provincia provincia(String provincia);

    Municipio municipio(String municipio);

    Departamento departamento(String departamento);

    Direccion direccion(String departamento, String direccion);
}
