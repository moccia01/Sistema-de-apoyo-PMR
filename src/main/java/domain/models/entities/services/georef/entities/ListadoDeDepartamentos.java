package domain.models.entities.services.georef.entities;

import lombok.Getter;

import java.util.List;

@Getter
public class ListadoDeDepartamentos {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Departamento> departamentos;

    private class Parametro {
        public List<String> campos;
    }
}