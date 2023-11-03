package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.EntidadPrestadora;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.*;
import java.util.List;

public class RepositorioEntidadesPrestadoras implements WithSimplePersistenceUnit {

    public void agregar(EntidadPrestadora entidadPrestadora){
        if(this.obtenerEntidadPrestadora(entidadPrestadora.getNombre()) == null){
            EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(entidadPrestadora);
            tx.commit();
        }
    }

    public List<EntidadPrestadora> obtenerEntidadesPrestadoras() {
        String jpql = "SELECT e FROM EntidadPrestadora e";
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        TypedQuery<EntidadPrestadora> query = entityManager.createQuery(jpql, EntidadPrestadora.class);
        tx.begin();
        List<EntidadPrestadora> entidades = query.getResultList();
        tx.commit();
        return entidades;
    }

    public EntidadPrestadora obtenerEntidadPrestadora(String id){
        EntidadPrestadora entidadPrestadora = null;
        String jpql = "SELECT e FROM EntidadPrestadora e " +
                "WHERE e.nombre = :id";
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
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
