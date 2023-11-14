package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.EntidadPrestadora;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.*;
import java.util.List;

public class RepositorioEntidadesPrestadoras{

    public void agregar(EntidadPrestadora entidadPrestadora, EntityManager entityManager){
        if(this.obtenerEntidadPrestadora(entidadPrestadora.getNombre(), entityManager) == null){
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(entidadPrestadora);
            tx.commit();
        }
    }

    public List<EntidadPrestadora> obtenerEntidadesPrestadoras(EntityManager entityManager) {
        String jpql = "SELECT e FROM EntidadPrestadora e";
        EntityTransaction tx = entityManager.getTransaction();
        TypedQuery<EntidadPrestadora> query = entityManager.createQuery(jpql, EntidadPrestadora.class);
        tx.begin();
        List<EntidadPrestadora> entidades = query.getResultList();
        tx.commit();
        return entidades;
    }

    public EntidadPrestadora obtenerEntidadPrestadora(String id, EntityManager entityManager){
        EntidadPrestadora entidadPrestadora = null;
        String jpql = "SELECT e FROM EntidadPrestadora e " +
                "WHERE e.nombre = :id";
        EntityTransaction tx = entityManager.getTransaction();
        TypedQuery<EntidadPrestadora> query = entityManager.createQuery(jpql, EntidadPrestadora.class);
        try{
            tx.begin();
            query.setParameter("id", id);
            entidadPrestadora = query.getSingleResult();
            tx.commit();
        } catch (NoResultException e){
            entidadPrestadora = null;
        } finally {
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
        return entidadPrestadora;
    }
}
