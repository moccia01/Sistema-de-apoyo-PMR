package domain.services.georef;

import domain.services.georef.entities.Departamento;
import domain.services.georef.entities.Direccion;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;

import java.io.IOException;

public interface Localizador {

    Provincia provincia(String provincia);

    Municipio municipio(String municipio) throws IOException;

    Departamento departamento(String departamento) throws IOException;

    Direccion direccion(String departamento, String direccion);
}