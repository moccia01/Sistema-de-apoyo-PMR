package domain.services.georef.entities;

import java.util.List;
import java.util.Optional;

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