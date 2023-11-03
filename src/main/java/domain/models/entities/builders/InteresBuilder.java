package domain.models.entities.builders;

import domain.models.entities.builders.exceptions.InteresSinEntidadesException;
import domain.models.entities.builders.exceptions.InteresSinServiciosException;
import domain.models.entities.comunidad.Interes;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Servicio;

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
        Interes interesARetornar = this.interes;
        this.interes = new Interes();
        return interesARetornar;
    }
}
