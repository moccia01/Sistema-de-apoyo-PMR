package domain.models.repositorios;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.converters.GradoDeConfianzaConstructor;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RepositorioGradosDeConfianza implements WithSimplePersistenceUnit {

    public GradoDeConfianza obtenerGradoDeConfianza(NombreGradoConfianza nombreGradoConfianza) {
        GradoDeConfianza gradoDeConfianza = null;
        EntityTransaction tx = entityManager().getTransaction();
        TypedQuery<GradoDeConfianza> query = entityManager().createQuery(
                "SELECT g FROM GradoDeConfianza g WHERE g.nombreGradoConfianza = :nombre",
                GradoDeConfianza.class
        );
        try {
            tx.begin();
            query.setParameter("nombre", nombreGradoConfianza);
            gradoDeConfianza = query.getSingleResult();
            tx.commit();
        }catch (NoResultException e){
            gradoDeConfianza = null;
        } finally {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        return gradoDeConfianza;
    }

    public void agregar(GradoDeConfianza gradoDeConfianza){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(gradoDeConfianza);
        tx.commit();
    }
}
