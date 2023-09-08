package domain.mensajes;

import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import domain.repositorios.RepoUsuarios;
import domain.repositorios.RepositorioComunidades;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesPendientesSender {


    public NotificacionesPendientesSender() {

    }

    public static void agregarMiembros(List<Miembro> listaMiembros){

    }

    public static void main(String[] args) {
        NotificacionesPendientesSender.mandarPendientes(new RepoUsuarios().obtenerUsuarios());
    }

    public static void mandarPendientes(List<Usuario> usuarios){
        usuarios.forEach(Usuario::mandarPendientes);
    }
}
