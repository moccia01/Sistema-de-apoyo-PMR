package domain.models.entities.localizacion;

import domain.models.entities.mensajes.Notificaciones.SugerenciaRevision;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.models.repositorios.RepositorioMiembros;
import domain.models.repositorios.RepositorioUsuarios;
import domain.server.Server;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class LocalizacionUpdater {
    private static SistemaPosicionamiento sistemaPosicionamiento;

    public LocalizacionUpdater(SistemaPosicionamiento sistema) {
        sistemaPosicionamiento = sistema;
    }

    public static void actualizarLocalizaciones(Usuario usuario){
        usuario.setLocalizacion(sistemaPosicionamiento.getPosicion(usuario.getTelefono()));
        notificarIncidentesCercanos(usuario);
    }

    public static void notificarIncidentesCercanos(Usuario usuario){
        List<Incidente> incidentesCercanosEInteresantes = usuario.obtenerComunidades()
                .stream().flatMap(c -> c.getIncidentes().stream())
                .filter(i -> usuario.estaInteresadoEn(i) && usuario.estaCercaDe(i))
                .toList();
        incidentesCercanosEInteresantes.forEach(i -> new SugerenciaRevision().notificar(usuario, i));
    }

    // Este main se deberia correr con un cron del sistema operativo
    public static void main(String[] args) {
        List<Usuario> usuarios = new RepositorioUsuarios().obtenerUsuarios();
        usuarios.forEach(LocalizacionUpdater::actualizarLocalizaciones);
    }
}
