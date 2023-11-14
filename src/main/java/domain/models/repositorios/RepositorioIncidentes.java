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

    public List<Incidente> obtenerIncidentesDe(Long usuario_id, EntityManager entityManager) {
        String jpql = "SELECT i FROM Comunidad c " +
                "JOIN c.incidentes i " +
                "JOIN c.miembros m " +
                "WHERE m.usuario.id = :usuario";

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Incidente> query = entityManager.createQuery(jpql, Incidente.class);
        query.setParameter("usuario", usuario_id);
        List<Incidente> incidentes = query.getResultList();
        tx.commit();
        return incidentes;
    }

    public Incidente obtenerIncidente(Long id, EntityManager entityManager){
        // VER SI ESTA BIEN
        return entityManager.find(Incidente.class, id);
    }

    public void agregar(Incidente incidente, EntityManager entityManager){
        entityManager.persist(incidente);
    }

    public void modificar(Incidente incidente, EntityManager entityManager){
        entityManager.merge(incidente);
    }

    public void eliminar(Incidente incidente, EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(incidente);
        tx.commit();
    }
}
