package domain;

import lombok.Getter;

@Getter
public class EntidadPrestadora {
    private String nombreEntidad;

    public EntidadPrestadora(String nombre) {
        nombreEntidad = nombre;
    }
}

