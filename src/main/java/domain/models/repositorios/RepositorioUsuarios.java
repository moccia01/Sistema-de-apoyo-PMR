package domain.models.repositorios;

import domain.models.entities.comunidad.Usuario;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioUsuarios implements WithSimplePersistenceUnit{

    public List<Usuario> obtenerUsuarios(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }

    public Usuario obtenerUsuario(CredencialDeAcceso credencialDeAcceso, EntityManager entityManager) {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.credencialDeAcceso = :credencial_id", Usuario.class);
        query.setParameter("credencial_id", credencialDeAcceso);
        return query.getSingleResult();
    }

    public Usuario obtenerUsuarioSegun(Long id, EntityManager entityManager) {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.id = :id", Usuario.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
