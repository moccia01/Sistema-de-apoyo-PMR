package domain.models.repositorios;

import domain.models.entities.comunidad.Miembro;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioMiembros {
    public List<Miembro> obtenerMiembros(EntityManager entityManager){
        return entityManager
                .createQuery("from Comunidad")
                .getResultList();
    }

    public Miembro obtenerMiembro(Long miembro_id, EntityManager entityManager) {
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.id = :miembro_id";

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Miembro> query = entityManager.createQuery(jpql, Miembro.class);
        query.setParameter("miembro_id", miembro_id);
        Miembro miembro = query.getSingleResult();
        tx.commit();
        return miembro;
    }

    public void agregar(Miembro miembro, EntityManager entityManager) {
        entityManager.persist(miembro);
    }

    public void modificar(Miembro miembro, EntityManager entityManager){
        entityManager.merge(miembro);
    }

    public void eliminar(Miembro miembro, EntityManager entityManager) {
        entityManager.remove(miembro);
    }

    public List<Miembro> obtenerMiembrosDe(Long usuario_id, EntityManager entityManager) {
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.usuario.id = :usuario";

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Miembro> query = entityManager.createQuery(jpql, Miembro.class);
        query.setParameter("usuario", usuario_id);
        List<Miembro> miembros = query.getResultList();
        tx.commit();
        return miembros;
    }

    public Miembro obtenerMiembroDe(Long usuario_id, Long comunidad_id, EntityManager entityManager){
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.usuario.id = :usuario_id AND m.comunidad.id = :comunidad_id";

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Miembro> query = entityManager.createQuery(jpql, Miembro.class);
        query.setParameter("usuario_id", usuario_id);
        query.setParameter("comunidad_id", comunidad_id);
        Miembro miembro = query.getSingleResult();
        tx.commit();
        return miembro;
    }
}
