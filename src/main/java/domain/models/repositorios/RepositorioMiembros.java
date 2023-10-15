package domain.models.repositorios;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
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

    public Miembro obtenerMiembro(Long miembro_id) {
        String jpql = "SELECT m FROM Miembro m " +
                "WHERE m.id = :miembro_id";
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<Miembro> query = entityManager().createQuery(jpql, Miembro.class);
        query.setParameter("miembro_id", miembro_id);
        Miembro miembro = query.getSingleResult();
        tx.commit();
        return miembro;
    }

    public void agregar(Miembro miembro) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(miembro);
        tx.commit();
    }

    public void modificar(Miembro miembro){
        // VER SI ESTA BIEN
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(miembro);
        tx.commit();
    }

    public void eliminar(Miembro miembro) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(miembro);
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
