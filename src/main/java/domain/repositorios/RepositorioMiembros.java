package domain.repositorios;

import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMiembros implements WithSimplePersistenceUnit {
    public List<Miembro> obtenerMiembros(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
}
