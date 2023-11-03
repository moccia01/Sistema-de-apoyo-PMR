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

public class RepositorioTiemposConfiguracion implements WithSimplePersistenceUnit {

    public TiempoConfigurado obtenerTiempoConfigurado(String discriminador) {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<TiempoConfigurado> query = entityManager.createQuery(
                "SELECT t FROM tiempo_configurado t WHERE type(t) = :discriminador",
                TiempoConfigurado.class
        );
        query.setParameter("discriminador", discriminador);
        TiempoConfigurado tiempo = query.getSingleResult();
        tx.commit();
        return tiempo;
    }

    public TiempoConfigurado obtenerTiempoConfigurado(Long id) {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<TiempoConfigurado> query = entityManager.createQuery(
                "SELECT t FROM tiempo_configurado t WHERE id = :id",
                TiempoConfigurado.class
        );
        query.setParameter("id", id);
        TiempoConfigurado tiempo = query.getSingleResult();
        tx.commit();
        return tiempo;
    }

    public TiempoConfigurado obtenerConfigCuandoSucede() {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<TiempoConfigurado> query = entityManager.createQuery(
                "SELECT t FROM tiempo_configurado t WHERE type(t) = domain.models.entities.mensajes.Configuraciones.CuandoSucede",
                TiempoConfigurado.class
        );
        TiempoConfigurado tiempoConfigurado = query.getSingleResult();
        tx.commit();
        return tiempoConfigurado;
    }

}
