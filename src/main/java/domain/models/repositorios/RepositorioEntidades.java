package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEntidades implements WithSimplePersistenceUnit {

    public List<Entidad> obtenerEntidades(){
        return entityManager()
                .createQuery("from Entidad ")
                .getResultList();
    }

    public void agregar(Entidad entidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(entidad);
        tx.commit();
    }

    public Entidad obtenerEntidad(Long id) {
        return entityManager().find(Entidad.class, id);
    }
}
