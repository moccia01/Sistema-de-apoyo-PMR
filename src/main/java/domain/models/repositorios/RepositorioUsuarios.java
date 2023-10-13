package domain.models.repositorios;

import domain.models.entities.comunidad.Usuario;
import domain.models.entities.validaciones.CredencialDeAcceso;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioUsuarios implements WithSimplePersistenceUnit {

    //TODO creor usuario admin para el sistema y encargados de inicializar el sistema (localizaciones, etc)
    public List<Usuario> obtenerUsuarios(){
        return entityManager()
                .createQuery("from Comunidad")
                .getResultList();
    }

    public void agregar(Usuario usuario) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(usuario);
        tx.commit();
    }

    public Usuario obtenerUsuario(CredencialDeAcceso credencialDeAcceso) {
        //TODO VER SI FUNCA
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        TypedQuery<Usuario> query = entityManager().createQuery("SELECT u FROM Usuario u WHERE u.credencialDeAcceso = :credencial_id", Usuario.class);
        query.setParameter("credencial_id", credencialDeAcceso);
        Usuario usuario = query.getSingleResult();
        tx.commit();
        return usuario;
    }

    //TODO VER SI ESTO FUNCIONA, es para chequear el rol del miembro de cada usuario
    public Usuario obtenerUsuarioSegunId(int id){
        Usuario usuario = new Usuario();
        usuario= entityManager().createQuery(" from Usuario  where id = :n", Usuario.class)
                .setParameter("n",id)
                .getSingleResult();
        return usuario;
    }

}
