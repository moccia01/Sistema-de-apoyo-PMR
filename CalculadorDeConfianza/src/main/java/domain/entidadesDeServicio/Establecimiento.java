package domain.entidadesDeServicio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Establecimiento{

    private String nombre;


    private List<Servicio> servicios;

    public Establecimiento() {
        this.servicios = new ArrayList<>();
    }

    public void agregarServicios(Servicio ... servicios1) {
        Collections.addAll(this.servicios, servicios1);
    }
}