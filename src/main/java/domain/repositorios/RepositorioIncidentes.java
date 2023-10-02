package domain.repositorios;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioIncidentes implements WithSimplePersistenceUnit {
    public List<Incidente> obtenerIncidentes(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
}
