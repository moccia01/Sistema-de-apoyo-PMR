package domain.models.entities.mensajes;

import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.models.repositorios.RepositorioUsuarios;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import java.util.List;

public class NotificacionesPendientesSender implements WithSimplePersistenceUnit {


    public NotificacionesPendientesSender(){

    }

    public static void agregarMiembros(List<Miembro> listaMiembros){

    }

    public static void main(String[] args) {
        NotificacionesPendientesSender.mandarPendientes(new RepositorioUsuarios().obtenerUsuarios());
    }

    public static void mandarPendientes(List<Usuario> usuarios){
        usuarios.forEach(Usuario::mandarPendientes);
    }
}
