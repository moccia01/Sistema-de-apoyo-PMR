package domain.entidadesDeServicio;

import lombok.Getter;

import java.util.List;

@Getter
public class EntidadPrestadora {
    private final String nombreEntidad;
    private List<Entidad> entidades;

    public EntidadPrestadora(String nombre) {
        nombreEntidad = nombre;
    }

    public boolean estaAsociadoA(Entidad entidad){
        return this.entidades.contains(entidad);
    }

}

