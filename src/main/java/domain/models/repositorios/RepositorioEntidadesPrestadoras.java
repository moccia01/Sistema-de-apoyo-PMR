package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.EntidadPrestadora;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RepositorioEntidadesPrestadoras implements WithSimplePersistenceUnit {

    public void agregar(EntidadPrestadora entidadPrestadora){
        if(this.obtenerEntidadPrestadora(entidadPrestadora.getId()) == null){
            EntityTransaction tx = entityManager().getTransaction();
            tx.begin();
            entityManager().persist(entidadPrestadora);
            tx.commit();
        }
    }

    public EntidadPrestadora obtenerEntidadPrestadora(Long id){
        EntidadPrestadora entidadPrestadora = null;
        String jpql = "SELECT e FROM EntidadPrestadora e " +
                "WHERE e.id = :id";
        EntityTransaction tx = entityManager().getTransaction();
        TypedQuery<EntidadPrestadora> query = entityManager().createQuery(jpql, EntidadPrestadora.class);
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
