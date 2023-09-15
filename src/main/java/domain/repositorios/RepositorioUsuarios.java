package domain.repositorios;

import domain.comunidad.Comunidad;
import domain.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioUsuarios implements WithSimplePersistenceUnit {

    //TODO creor usuario admin para el sistema y encargados de inicializar el sistema (localizaciones, etc)
    public List<Usuario> obtenerUsuarios(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }
}
