package domain;

import domain.entidadesDeServicio.Entidad;

import java.util.List;

public class OrganismoDeControl {
    private String nombre;
    private List<Entidad> entidades;

    public OrganismoDeControl(String nombre) {
        nombre = nombre;
    }
}
