package domain.models.repositorios.localizaciones;

import domain.models.entities.services.georef.entities.Municipio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;

public class RepositorioMunicipios implements WithSimplePersistenceUnit {

    public void agregar(Municipio municipio) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(municipio);
        tx.commit();
    }
}
