package domain.models.repositorios;

import domain.models.entities.admins.AdminDePlataforma;
import domain.models.entities.validaciones.CredencialDeAcceso;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RepositorioAdmins implements WithSimplePersistenceUnit {

    public void agregar(AdminDePlataforma admin) {
        entityManager().persist(admin);
    }

    public AdminDePlataforma obtenerAdmin(String nombreUsuario, String contrasenia) {
        EntityTransaction tx = entityManager().getTransaction();
        AdminDePlataforma adminDePlataforma;
        try {
            tx.begin();
            TypedQuery<AdminDePlataforma> query = entityManager().createQuery(
                    "SELECT a FROM AdminDePlataforma a WHERE a.usuario = :nombreUsuario AND a.contrasenia = :contrasenia", AdminDePlataforma.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("contrasenia", contrasenia);
            adminDePlataforma = query.getSingleResult();
            tx.commit();
        } catch (NoResultException ignored) {
            adminDePlataforma = null;
        } finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        return adminDePlataforma;
    }

}
