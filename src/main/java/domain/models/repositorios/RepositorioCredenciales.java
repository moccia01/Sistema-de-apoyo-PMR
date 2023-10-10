package domain.models.repositorios;


import domain.models.entities.validaciones.CredencialDeAcceso;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


public class RepositorioCredenciales implements WithSimplePersistenceUnit {

    public CredencialDeAcceso obtenerCredencial(String username, String password) {
        //TODO VER SI FUNCA
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<CredencialDeAcceso> query = entityManager().createQuery(
                "SELECT c FROM credencial c WHERE c.username = :username AND c.password = :password", CredencialDeAcceso.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        CredencialDeAcceso credencialDeAcceso = query.getSingleResult();
        tx.commit();
        return credencialDeAcceso;
    }
}
