package domain.models.repositorios;

import domain.models.entities.admins.AdminDePlataforma;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RepositorioAdmins {

    public AdminDePlataforma obtenerAdmin(String nombreUsuario, String contrasenia, EntityManager entityManager) {
        AdminDePlataforma adminDePlataforma;
        try {
            TypedQuery<AdminDePlataforma> query = entityManager.createQuery(
                    "SELECT a FROM AdminDePlataforma a WHERE a.usuario = :nombreUsuario AND a.contrasenia = :contrasenia", AdminDePlataforma.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("contrasenia", contrasenia);
            adminDePlataforma = query.getSingleResult();
        } catch (NoResultException ignored) {
            adminDePlataforma = null;
        }
        return adminDePlataforma;
    }

}
