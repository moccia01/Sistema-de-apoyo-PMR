package domain.models.repositorios;


import domain.models.entities.validaciones.CredencialDeAcceso;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class RepositorioCredenciales implements WithSimplePersistenceUnit {

    public CredencialDeAcceso obtenerCredencial(String nombreUsuario, String contrasenia) {
        EntityTransaction tx = entityManager().getTransaction();
        CredencialDeAcceso credencialDeAcceso;
        try {
            tx.begin();
            TypedQuery<CredencialDeAcceso> query = entityManager().createQuery(
                "SELECT c FROM CredencialDeAcceso c WHERE c.nombreUsuario = :nombreUsuario AND c.contrasenia = :contrasenia", CredencialDeAcceso.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("contrasenia", contrasenia);
            credencialDeAcceso = query.getSingleResult();
            tx.commit();
        } catch (NoResultException ignored) {
            credencialDeAcceso = null;
        } finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        return credencialDeAcceso;
    }
}
