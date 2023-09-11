package domain.repositorios;

import domain.comunidades.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepoMiembros implements WithSimplePersistenceUnit {

    public List<Usuario> obtenerMiembros(){

        return entityManager().createQuery("from miembro").getResultList();
    }
}
