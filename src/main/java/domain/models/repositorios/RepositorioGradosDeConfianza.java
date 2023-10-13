package domain.models.repositorios;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.NombreGradoConfianza;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class RepositorioGradosDeConfianza implements WithSimplePersistenceUnit {

    public GradoDeConfianza obtenerGradoDeConfianza(NombreGradoConfianza nombreGradoConfianza) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<GradoDeConfianza> query = entityManager().createQuery(
                "SELECT g FROM GradoDeConfianza g WHERE g.nombreGradoConfianza = :nombre",
                GradoDeConfianza.class
        );
        query.setParameter("nombre", nombreGradoConfianza);
        GradoDeConfianza gradoDeConfianza = query.getSingleResult();
        tx.commit();
        return gradoDeConfianza;
    }
}
