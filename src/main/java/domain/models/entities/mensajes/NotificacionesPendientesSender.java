package domain.models.entities.mensajes;

import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.models.repositorios.RepositorioUsuarios;

import java.util.List;

public class NotificacionesPendientesSender {


    public NotificacionesPendientesSender() {

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
