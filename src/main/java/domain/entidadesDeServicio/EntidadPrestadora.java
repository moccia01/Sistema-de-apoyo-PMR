package domain.entidadesDeServicio;

import domain.entidadesDeServicio.Entidad;
import lombok.Getter;

import java.util.List;

@Getter
public class EntidadPrestadora {
    private String nombreEntidad;
    private List<Entidad> entidades;

    public EntidadPrestadora(String nombre) {
        nombreEntidad = nombre;
    }
}

