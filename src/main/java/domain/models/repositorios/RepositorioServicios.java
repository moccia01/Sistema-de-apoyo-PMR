package domain.models.repositorios;

import domain.models.entities.comunidad.Miembro;
import domain.models.entities.entidadesDeServicio.Servicio;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioServicios {

    public List<Servicio> obtenerServicios(EntityManager entityManager){
        return entityManager
                .createQuery("from Servicio ")
                .getResultList();
    }

}
