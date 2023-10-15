package domain.models.repositorios;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioMiembros implements WithSimplePersistenceUnit {
    public List<Miembro> obtenerMiembros(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }

    public void agregar(Miembro miembro) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(miembro);
        tx.commit();
    }

    public List<Miembro> obtenerMiembrosDe(Long usuario_id) {
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.usuario.id = :usuario";
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<Miembro> query = entityManager().createQuery(jpql, Miembro.class);
        query.setParameter("usuario", usuario_id);
        List<Miembro> miembros = query.getResultList();
        tx.commit();
        return miembros;
    }

    public Miembro obtenerMiembroDe(Long usuario_id, Long comunidad_id){
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.usuario.id = :usuario_id AND m.comunidad.id = :comunidad_id";
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<Miembro> query = entityManager().createQuery(jpql, Miembro.class);
        query.setParameter("usuario_id", usuario_id);
        query.setParameter("comunidad_id", comunidad_id);
        Miembro miembro = query.getSingleResult();
        tx.commit();
        return miembro;
    }
}
