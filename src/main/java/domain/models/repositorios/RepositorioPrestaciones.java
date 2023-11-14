package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.entidadesDeServicio.Servicio;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class RepositorioPrestaciones {

    public void agregar(PrestacionDeServicio prestacionDeServicio, EntityManager entityManager){
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(prestacionDeServicio);
        tx.commit();
    }

    public PrestacionDeServicio obtenerPrestacion(Long entidad_id, Long establecimiento_id, Long servicio_id, EntityManager entityManager) {
        String hql = "SELECT p FROM PrestacionDeServicio p " +
                     "WHERE p.entidad.id = :entidad_id " +
                     "AND p.establecimiento.id = :establecimiento_id " +
                     "AND p.servicio.id = :servicio_id";

        TypedQuery<PrestacionDeServicio> query = entityManager.createQuery(hql, PrestacionDeServicio.class);
        query.setParameter("entidad_id", entidad_id);
        query.setParameter("establecimiento_id", establecimiento_id);
        query.setParameter("servicio_id", servicio_id);
        return query.getSingleResult();
    }

}
