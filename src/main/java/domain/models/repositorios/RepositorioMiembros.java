package domain.models.repositorios;

import domain.models.entities.comunidad.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepositorioMiembros implements WithSimplePersistenceUnit {
    public List<Miembro> obtenerMiembros(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
}
