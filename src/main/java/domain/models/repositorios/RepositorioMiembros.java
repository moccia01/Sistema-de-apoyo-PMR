package domain.models.repositorios;

import domain.models.entities.comunidad.Miembro;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioMiembros implements WithSimplePersistenceUnit{

    public Miembro obtenerMiembro(Long miembro_id, EntityManager entityManager) {
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.id = :miembro_id";
        TypedQuery<Miembro> query = entityManager.createQuery(jpql, Miembro.class);
        query.setParameter("miembro_id", miembro_id);
        return query.getSingleResult();
    }

    public List<Miembro> obtenerMiembrosDe(Long usuario_id, EntityManager entityManager) {
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.usuario.id = :usuario";
        TypedQuery<Miembro> query = entityManager.createQuery(jpql, Miembro.class);
        query.setParameter("usuario", usuario_id);
        return query.getResultList();
    }

    public Miembro obtenerMiembroDe(Long usuario_id, Long comunidad_id, EntityManager entityManager){
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.usuario.id = :usuario_id AND m.comunidad.id = :comunidad_id";
        TypedQuery<Miembro> query = entityManager.createQuery(jpql, Miembro.class);
        query.setParameter("usuario_id", usuario_id);
        query.setParameter("comunidad_id", comunidad_id);
        return query.getSingleResult();
    }
}
