package domain.models.entities.builders;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Miembro;

import java.util.ArrayList;
import java.util.List;

public class ComunidadBuilder {
    private Comunidad comunidad;

    public ComunidadBuilder(){
        this.comunidad = new Comunidad();
    }

    public ComunidadBuilder conNombre(String nombre){
        this.comunidad.setNombre(nombre);
        return this;
    }

    public ComunidadBuilder conMiembros(Miembro ... miembros){
        this.comunidad.agregarMiembros(miembros);
        return this;
    }

    public ComunidadBuilder conIncidentes(List<Incidente> incidentes){
        incidentes = new ArrayList<>();
        this.comunidad.setIncidentes(incidentes);
        return this;
    }
}
