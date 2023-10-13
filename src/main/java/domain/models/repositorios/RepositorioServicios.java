package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepositorioServicios implements WithSimplePersistenceUnit {

    public List<Servicio> obtenerServicios(){
        return entityManager()
                .createQuery("from Servicio ")
                .getResultList();
    }

    public Servicio obtenerServicio(Long id) {
        return entityManager().find(Servicio.class, id);
    }
}
