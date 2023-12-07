package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.OrganismoDeControl;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioOrganismoDeControl {

    public void agregar(OrganismoDeControl organismoDeControl, EntityManager entityManager){
        if(this.obtenerOrganismoDeControl(organismoDeControl.getNombre(), entityManager) == null){
            entityManager.persist(organismoDeControl);
        }
    }

    public List<OrganismoDeControl> obtenerOrganismosDeControl(EntityManager entityManager){
        String jpql = "SELECT e FROM OrganismoDeControl e";
        TypedQuery<OrganismoDeControl> query = entityManager.createQuery(jpql, OrganismoDeControl.class);
        return query.getResultList();
    }

    public OrganismoDeControl obtenerOrganismoDeControl(String id, EntityManager entityManager){
        OrganismoDeControl organismoDeControl;
        String jpql = "SELECT o FROM OrganismoDeControl o " +
                "WHERE o.nombre = :id";
        TypedQuery<OrganismoDeControl> query = entityManager.createQuery(jpql, OrganismoDeControl.class);
        try {
            query.setParameter("id", id);
            organismoDeControl = query.getSingleResult();
        } catch (NoResultException e){
            organismoDeControl = null;
        }
        return organismoDeControl;
    }
}
