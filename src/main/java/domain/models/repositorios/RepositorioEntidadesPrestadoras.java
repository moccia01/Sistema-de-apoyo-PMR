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
            entityManager.persist(entidadPrestadora);
        }
    }

    public List<EntidadPrestadora> obtenerEntidadesPrestadoras(EntityManager entityManager) {
        String jpql = "SELECT e FROM EntidadPrestadora e";
        TypedQuery<EntidadPrestadora> query = entityManager.createQuery(jpql, EntidadPrestadora.class);
        return query.getResultList();
    }

    public EntidadPrestadora obtenerEntidadPrestadora(String nombre, EntityManager entityManager){
        EntidadPrestadora entidadPrestadora;
        String jpql = "SELECT e FROM EntidadPrestadora e " +
                "WHERE e.nombre = :nombre";
        TypedQuery<EntidadPrestadora> query = entityManager.createQuery(jpql, EntidadPrestadora.class);
        try{
            query.setParameter("nombre", nombre);
            entidadPrestadora = query.getSingleResult();
        } catch (NoResultException e){
            entidadPrestadora = null;
        }
        return entidadPrestadora;
    }
}
