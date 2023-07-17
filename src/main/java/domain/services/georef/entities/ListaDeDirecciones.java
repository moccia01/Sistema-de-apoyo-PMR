package domain.services.georef.entities;

import java.util.List;

public class ListaDeDirecciones {
    public int cantidad;
    public int total;
    public int inicio;

    public List<Direccion> direcciones;

    private class Parametro {
        public List<String> campos;
    }
}
