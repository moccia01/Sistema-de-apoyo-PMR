package domain.models.repositorios;

import domain.models.entities.comunidad.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioIncidentes implements WithSimplePersistenceUnit {
    public List<Incidente> obtenerIncidentes(){
        return entityManager()
                .createQuery("from Incidente")
                .getResultList();
    }

    public Incidente obtenerIncidente(int id){
        // VER SI ESTA BIEN
        return entityManager().find(Incidente.class, id);
    }

    public void agregar(Incidente incidente){
        // VER SI ESTA BIEN
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(incidente);
        tx.commit();
    }

    public void modificar(Incidente incidente){
        // VER SI ESTA BIEN
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(incidente);
        tx.commit();
    }

    public void eliminar(Incidente incidente) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(incidente);
        tx.commit();
    }
}
