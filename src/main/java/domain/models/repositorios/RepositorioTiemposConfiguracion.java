package domain.models.repositorios;

import domain.models.entities.converters.TiempoConfiguradoAttributeConverter;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class RepositorioTiemposConfiguracion {

    public TiempoConfigurado obtenerConfigCuandoSucede(EntityManager entityManager) {
        TypedQuery<TiempoConfigurado> query = entityManager.createQuery(
                "SELECT t FROM tiempo_configurado t WHERE type(t) = domain.models.entities.mensajes.Configuraciones.CuandoSucede",
                TiempoConfigurado.class
        );
        return query.getSingleResult();
    }

}
