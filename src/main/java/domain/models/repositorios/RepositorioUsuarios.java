package domain.models.repositorios;

import domain.models.entities.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepositorioUsuarios implements WithSimplePersistenceUnit {

    //TODO creor usuario admin para el sistema y encargados de inicializar el sistema (localizaciones, etc)
    public List<Usuario> obtenerUsuarios(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
}
