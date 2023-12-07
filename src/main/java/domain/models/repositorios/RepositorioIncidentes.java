package domain.models.repositorios;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioIncidentes {
    public List<Incidente> obtenerIncidentes(EntityManager entityManager){
        return entityManager
                .createQuery("from Incidente")
                .getResultList();
    }

    public Incidente obtenerIncidente(Long id, EntityManager entityManager){
        return entityManager.find(Incidente.class, id);
    }

}
