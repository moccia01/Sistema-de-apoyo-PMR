package domain.builders;

import domain.builders.exceptions.InteresSinEntidadesException;
import domain.builders.exceptions.InteresSinServiciosException;
import domain.comunidad.Interes;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Servicio;

import java.util.Collections;

public class InteresBuilder {
    private Interes interes;

    public InteresBuilder() {
        this.interes = new Interes();
    }

    public InteresBuilder agregarEntidades(Entidad ... entidades) {
        this.interes.agregarEntidades(entidades);
        return this;
    }

    public InteresBuilder agregarServicios(Servicio ... servicios) {
        this.interes.agregarServicios(servicios);
        return this;
    }

    public Interes construir() {
        // No veo la necesidad de generar excepciones
        if(this.interes.getEntidades() == null){
            throw new InteresSinEntidadesException();
        }
        if(this.interes.getServicios() == null){
            throw new InteresSinServiciosException();
        }

        return interes;
    }
}
