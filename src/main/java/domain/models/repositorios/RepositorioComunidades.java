package domain.models.repositorios;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Getter
public class RepositorioComunidades implements WithSimplePersistenceUnit {

    public List<Comunidad> obtenerComunidades(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
    public Comunidad obtenerComunidad(Long id){
        // VER SI ESTA BIEN
        return entityManager().find(Comunidad.class, id);
    }

    public List<Comunidad> obtenerComunidadesDe(Long usuario_id) {
        String jpql = "SELECT c FROM Comunidad c " +
                "JOIN c.miembros m " +
                "WHERE m.usuario.id = :usuario";
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<Comunidad> query = entityManager().createQuery(jpql, Comunidad.class);
        query.setParameter("usuario", usuario_id);
        List<Comunidad> comunidades = query.getResultList();
        tx.commit();
        return comunidades;
    }

    public List<Miembro> obtenerMiembrosDe(Long comunidad_id) {
        //TODO
        return null;
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
