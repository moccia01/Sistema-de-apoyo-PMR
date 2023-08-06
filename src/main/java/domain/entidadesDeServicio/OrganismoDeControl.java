package domain.entidadesDeServicio;

import java.util.List;

public class OrganismoDeControl {
    private String nombre;
    private List<Entidad> entidades;

    public OrganismoDeControl(String nombre) {
        nombre = nombre;
    }

    public boolean estaAsociadoA(Entidad entidad) {
        return this.entidades.contains(entidad);
    }

}
