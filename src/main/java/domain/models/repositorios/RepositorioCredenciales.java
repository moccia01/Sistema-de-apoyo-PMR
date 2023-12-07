package domain.models.repositorios;


import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class RepositorioCredenciales {

    public CredencialDeAcceso obtenerCredencial(String nombreUsuario, String contrasenia, EntityManager entityManager) {
        CredencialDeAcceso credencialDeAcceso;
        try {
            TypedQuery<CredencialDeAcceso> query = entityManager.createQuery(
                "SELECT c FROM CredencialDeAcceso c WHERE c.nombreUsuario = :nombreUsuario AND c.contrasenia = :contrasenia", CredencialDeAcceso.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("contrasenia", contrasenia);
            credencialDeAcceso = query.getSingleResult();
        } catch (NoResultException ignored) {
            credencialDeAcceso = null;
        }
        return credencialDeAcceso;
    }

    public CredencialDeAcceso obtenerCredencialDe(String nombreUsuario, EntityManager entityManager) {
        CredencialDeAcceso credencialDeAcceso;
        try {
            TypedQuery<CredencialDeAcceso> query = entityManager.createQuery(
                    "SELECT c FROM CredencialDeAcceso c WHERE c.nombreUsuario = :nombreUsuario", CredencialDeAcceso.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            credencialDeAcceso = query.getSingleResult();
        } catch (NoResultException ignored) {
            credencialDeAcceso = null;
        }
        return credencialDeAcceso;
    }
}
