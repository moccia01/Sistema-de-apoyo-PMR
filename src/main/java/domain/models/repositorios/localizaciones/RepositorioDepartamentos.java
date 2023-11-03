package domain.models.repositorios.localizaciones;

import domain.models.entities.services.georef.entities.Departamento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;

public class RepositorioDepartamentos implements WithSimplePersistenceUnit {

    public void agregar(Departamento departamento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(departamento);
        tx.commit();
    }
}
