package domain.repositorios;

import domain.comunidades.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public interface RepoUsuarios {

    public List<Usuario> obtenerUsuarios();

}
