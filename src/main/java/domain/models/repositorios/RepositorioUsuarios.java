package domain.models.repositorios;

import domain.models.entities.comunidad.Usuario;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioUsuarios implements WithSimplePersistenceUnit {

    public List<Usuario> obtenerUsuarios(){
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        return entityManager
                .createQuery("from Comunidad")
                .getResultList();
    }

    public void agregar(Usuario usuario) {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        entityManager.persist(usuario);
    }

    public void modificar(Usuario usuario) {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        entityManager.merge(usuario);
    }

    public Usuario obtenerUsuario(CredencialDeAcceso credencialDeAcceso) {
        //TODO VER SI FUNCA
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.credencialDeAcceso = :credencial_id", Usuario.class);
        query.setParameter("credencial_id", credencialDeAcceso);
        Usuario usuario = query.getSingleResult();
        tx.commit();
        return usuario;
    }

    public Usuario obtenerUsuarioSegun(Long id) {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.id = :id", Usuario.class);
        query.setParameter("id", id);
        Usuario usuario = query.getSingleResult();
        tx.commit();
        return usuario;
    }
}
