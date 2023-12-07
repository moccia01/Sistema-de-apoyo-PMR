package domain.models.repositorios;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.converters.GradoDeConfianzaConstructor;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RepositorioGradosDeConfianza{

    public GradoDeConfianza obtenerGradoDeConfianza(EntityManager entityManager, NombreGradoConfianza nombreGradoConfianza) {
        GradoDeConfianza gradoDeConfianza;

        TypedQuery<GradoDeConfianza> query = entityManager.createQuery(
                "SELECT g FROM GradoDeConfianza g WHERE g.nombreGradoConfianza = :nombre",
                GradoDeConfianza.class
        );
        try {
            query.setParameter("nombre", nombreGradoConfianza);
            gradoDeConfianza = query.getSingleResult();
        }catch (NoResultException e){
            gradoDeConfianza = null;
        }
        return gradoDeConfianza;
    }

}
