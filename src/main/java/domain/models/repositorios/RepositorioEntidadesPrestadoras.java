package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.EntidadPrestadora;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class RepositorioEntidadesPrestadoras implements WithSimplePersistenceUnit {

    public void agregar(EntidadPrestadora entidadPrestadora){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(entidadPrestadora);
        tx.commit();
    }

    public EntidadPrestadora obtenerEntidadPrestadora(Long entidad_prestadora_id){
        String jpql = "SELECT e FROM EntidadPrestadora e " +
                "WHERE e.id = :entidad_prestadora_id";
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<EntidadPrestadora> query = entityManager().createQuery(jpql, EntidadPrestadora.class);
        query.setParameter("entidad_prestadora_id", entidad_prestadora_id);
        EntidadPrestadora entidadPrestadora = query.getSingleResult();
        tx.commit();
        return entidadPrestadora;
    }
}
