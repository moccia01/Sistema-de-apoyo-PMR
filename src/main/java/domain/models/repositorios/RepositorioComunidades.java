package domain.models.repositorios;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import javax.persistence.EntityTransaction;
import java.util.List;

@Getter
public class RepositorioComunidades implements WithSimplePersistenceUnit {

    public List<Comunidad> obtenerComunidades(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
    public Comunidad obtenerComunidad(int id){
        // VER SI ESTA BIEN
        return entityManager().find(Comunidad.class, id);
    }

    public void agregar(Comunidad comunidad){
        // VER SI ESTA BIEN
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(comunidad);
        tx.commit();
    }

    public void modificar(Comunidad comunidad){
        // VER SI ESTA BIEN
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(comunidad);
        tx.commit();
    }

    public void eliminar(Comunidad comunidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(comunidad);
        tx.commit();
    }
}
