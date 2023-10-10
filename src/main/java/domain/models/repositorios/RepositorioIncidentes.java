package domain.models.repositorios;

import domain.models.entities.comunidad.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioIncidentes implements WithSimplePersistenceUnit {
    public List<Incidente> obtenerIncidentes(){
        return entityManager()
                .createQuery("from Incidente")
                .getResultList();
    }

    public List<Incidente> obtenerIncidentesDe(int usuario_id) {
        String jpql = "SELECT i FROM Incidente i " +
                "JOIN i.comunidad_incidente ci " +
                "JOIN ci.comunidad c " +
                "JOIN c.miembros m " +
                "WHERE m.usuario.id = :usuarioId";
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<Incidente> query = entityManager().createQuery(jpql, Incidente.class);
        query.setParameter("usuarioId", usuario_id);
        List<Incidente> incidentes = query.getResultList();
        tx.commit();
        return incidentes;
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
