package domain.models.repositorios;


import domain.models.entities.validaciones.CredencialDeAcceso;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


public class RepositorioCredenciales implements WithSimplePersistenceUnit {

    public CredencialDeAcceso obtenerCredencial(String nombreUsuario, String contrasenia) {
        //TODO VER SI FUNCA
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<CredencialDeAcceso> query = entityManager().createQuery(
                "SELECT c FROM CredencialDeAcceso c WHERE c.nombreUsuario = :nombreUsuario AND c.contrasenia = :contrasenia", CredencialDeAcceso.class);
        query.setParameter("nombreUsuario", nombreUsuario);
        query.setParameter("contrasenia", contrasenia);
        CredencialDeAcceso credencialDeAcceso = query.getSingleResult();
        tx.commit();
        return credencialDeAcceso;
    }
}
