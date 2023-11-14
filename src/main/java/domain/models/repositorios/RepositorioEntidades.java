package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEntidades{

    public List<Entidad> obtenerEntidades(EntityManager entityManager){
        return entityManager
                .createQuery("from Entidad ")
                .getResultList();
    }

    public void agregar(Entidad entidad, EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(entidad);
        tx.commit();
    }

    public Entidad obtenerEntidad(Long id, EntityManager entityManager) {
        return entityManager.find(Entidad.class, id);
    }
}
