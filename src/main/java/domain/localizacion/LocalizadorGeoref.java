package domain.localizacion;

import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.Departamento;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;

import java.io.IOException;

public class LocalizadorGeoref implements Localizador{

    @Override
    public Provincia provincia(String provincia) throws IOException {
        ServicioGeoref georef = ServicioGeoref.instancia();
        return georef.provincia(provincia);
    }

    @Override
    public Municipio municipio(String municipio) throws IOException {
        ServicioGeoref georef = ServicioGeoref.instancia();
        return georef.municipio(municipio);
    }

    @Override
    public Departamento departamento(String departamento) {
        return null;
    }
}
