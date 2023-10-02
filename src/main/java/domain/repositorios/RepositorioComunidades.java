package domain.repositorios;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RepositorioComunidades implements WithSimplePersistenceUnit {

    public List<Comunidad> obtenerComunidades(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
}
