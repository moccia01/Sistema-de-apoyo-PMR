package domain.models.repositorios;

import domain.models.entities.comunidad.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepositorioIncidentes implements WithSimplePersistenceUnit {
    public List<Incidente> obtenerIncidentes(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
}
