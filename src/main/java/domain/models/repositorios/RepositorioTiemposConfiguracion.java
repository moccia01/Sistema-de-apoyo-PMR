package domain.models.repositorios;

import domain.models.entities.converters.TiempoConfiguradoAttributeConverter;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class RepositorioTiemposConfiguracion implements WithSimplePersistenceUnit {

    public TiempoConfigurado obtenerTiempoConfigurado(String discriminador) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<TiempoConfigurado> query = entityManager().createQuery(
                "SELECT t FROM tiempo_configurado t WHERE type(t) = :discriminador",
                TiempoConfigurado.class
        );
        query.setParameter("discriminador", discriminador);
        TiempoConfigurado tiempo = query.getSingleResult();
        tx.commit();
        return tiempo;
    }

    public TiempoConfigurado obtenerTiempoConfigurado(Long id) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<TiempoConfigurado> query = entityManager().createQuery(
                "SELECT t FROM tiempo_configurado t WHERE id = :id",
                TiempoConfigurado.class
        );
        query.setParameter("id", id);
        TiempoConfigurado tiempo = query.getSingleResult();
        tx.commit();
        return tiempo;
    }

    public TiempoConfigurado obtenerConfigCuandoSucede() {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<TiempoConfigurado> query = entityManager().createQuery(
                "SELECT t FROM tiempo_configurado t WHERE type(t) = domain.models.entities.mensajes.Configuraciones.CuandoSucede",
                TiempoConfigurado.class
        );
        TiempoConfigurado tiempoConfigurado = query.getSingleResult();
        tx.commit();
        return tiempoConfigurado;
    }

}
