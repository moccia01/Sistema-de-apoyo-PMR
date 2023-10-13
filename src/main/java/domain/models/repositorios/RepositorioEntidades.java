package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepositorioEntidades implements WithSimplePersistenceUnit {

    public List<Entidad> obtenerEntidades(){
        return entityManager()
                .createQuery("from Entidad ")
                .getResultList();
    }

    public Entidad obtenerEntidad(Long id) {
        return entityManager().find(Entidad.class, id);
    }
}
