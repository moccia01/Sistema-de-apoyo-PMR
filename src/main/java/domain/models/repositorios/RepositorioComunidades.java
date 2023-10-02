package domain.models.repositorios;

import domain.models.entities.comunidad.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import java.util.List;

@Getter
public class RepositorioComunidades implements WithSimplePersistenceUnit {

    public List<Comunidad> obtenerComunidades(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
}
