package domain.models.repositorios;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;
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

    public List<Incidente> obtenerIncidentesDe(Long usuario_id) {
        String jpql = "SELECT i FROM Comunidad c " +
                "JOIN c.incidentes i " +
                "JOIN c.miembros m " +
                "WHERE m.usuario.id = :usuario";

        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<Incidente> query = entityManager().createQuery(jpql, Incidente.class);
        query.setParameter("usuario", usuario_id);
        List<Incidente> incidentes = query.getResultList();
        tx.commit();
        return incidentes;
    }

    public Incidente obtenerIncidente(Long id){
        // VER SI ESTA BIEN
        return entityManager().find(Incidente.class, id);
    }



    public void agregar(Incidente incidente){
        // VER SI ESTA BIEN
        entityManager().persist(incidente);
    }

    public void modificar(Incidente incidente){
        // VER SI ESTA BIEN
        entityManager().merge(incidente);
    }

    public void eliminar(Incidente incidente) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(incidente);
        tx.commit();
    }
}
