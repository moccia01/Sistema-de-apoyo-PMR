package domain.models.repositorios;

import domain.models.entities.comunidad.Miembro;
import domain.models.entities.entidadesDeServicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioServicios implements WithSimplePersistenceUnit {

    public List<Servicio> obtenerServicios(){
        return entityManager()
                .createQuery("from Servicio ")
                .getResultList();
    }

    public void agregar(Servicio servicio) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(servicio);
        tx.commit();
    }

    public Servicio obtenerServicio(Long id) {
        return entityManager().find(Servicio.class, id);
    }
}
