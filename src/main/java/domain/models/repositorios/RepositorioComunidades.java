package domain.models.repositorios;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Getter
public class RepositorioComunidades {

    public List<Comunidad> obtenerComunidades(EntityManager entityManager){
        return entityManager
                .createQuery("from Comunidad")
                .getResultList();
    }
    public Comunidad obtenerComunidad(Long id, EntityManager entityManager){
        return entityManager.find(Comunidad.class, id);
    }

    public List<Comunidad> obtenerComunidadesDe(Long usuario_id, EntityManager entityManager) {
        String jpql = "SELECT c FROM Comunidad c " +
                "JOIN c.miembros m " +
                "WHERE m.usuario.id = :usuario";
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Comunidad> query = entityManager.createQuery(jpql, Comunidad.class);
        query.setParameter("usuario", usuario_id);
        List<Comunidad> comunidades = query.getResultList();
        tx.commit();
        return comunidades;
    }

    public List<Miembro> obtenerMiembrosDe(Long comunidad_id, EntityManager entityManager) {
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.comunidad.id = :comunidad_id";
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Miembro> query = entityManager.createQuery(jpql, Miembro.class);
        query.setParameter("comunidad_id", comunidad_id);
        List<Miembro> miembros = query.getResultList();
        tx.commit();
        return miembros;
    }

    public void agregar(Comunidad comunidad, EntityManager entityManager){
        // VER SI ESTA BIEN
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(comunidad);
        tx.commit();
    }

    public void modificar(Comunidad comunidad, EntityManager entityManager){
        entityManager.merge(comunidad);
    }

    public void eliminar(Comunidad comunidad, EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(comunidad);
        tx.commit();
    }
}
