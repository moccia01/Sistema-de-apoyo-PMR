package domain.models.entities.mensajes;

import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.models.repositorios.RepositorioUsuarios;
import domain.server.Server;

import javax.persistence.EntityManager;
import java.util.List;

public class NotificacionesPendientesSender {


    public NotificacionesPendientesSender() {

    }

    public static void agregarMiembros(List<Miembro> listaMiembros){

    }

    public static void main(String[] args) {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        NotificacionesPendientesSender.mandarPendientes(new RepositorioUsuarios().obtenerUsuarios(entityManager));
    }

    public static void mandarPendientes(List<Usuario> usuarios){
        usuarios.forEach(Usuario::mandarPendientes);
    }
}
