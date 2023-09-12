package domain.repositorios;

import domain.comunidad.Incidente;
import domain.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioIncidentes implements WithSimplePersistenceUnit {
    private List<Incidente> incidentes;

    public RepositorioIncidentes() {
        this.incidentes = new ArrayList<>();
    }

    public void agregarIncidentes(Incidente ... incidentes){
        Collections.addAll(this.incidentes, incidentes);
    }

    public List<Incidente> obtenerIncidentes(){
        if(this.incidentes == null){
            incidentes = entityManager()
                    .createQuery("from Incidente")
                    .getResultList();
        }
        return this.incidentes;
    }
}
