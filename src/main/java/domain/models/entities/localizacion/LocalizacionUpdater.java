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
    private static List<Usuario> usuarios;

    public LocalizacionUpdater(SistemaPosicionamiento sistema) {
        sistemaPosicionamiento = sistema;
        usuarios = new ArrayList<>();
    }

    public static void agregarUsuarios(List<Usuario> usuariosList){
        usuarios.addAll(usuariosList);
    }

    public static void actualizarLocalizaciones(EntityManager entityManager){
        usuarios.forEach(u -> u.setLocalizacion(sistemaPosicionamiento.getPosicion(u.getTelefono())));
        LocalizacionUpdater.verificarIncidentesCercanos(entityManager);
    }

    public static void verificarIncidentesCercanos(EntityManager entityManager){
        new RepositorioUsuarios().obtenerUsuarios(entityManager).forEach(LocalizacionUpdater::notificarIncidentesCercanos);
    }

    public static void notificarIncidentesCercanos(Usuario usuario){
        List<Incidente> incidentesCercanosEInteresantes = usuario.obtenerComunidades()
                .stream().flatMap(c -> c.getIncidentes().stream())
                .filter(i -> usuario.estaInteresadoEn(i) && usuario.estaCercaDe(i))
                .toList();
        incidentesCercanosEInteresantes.forEach(i -> new SugerenciaRevision().notificar(usuario, i));
    }


    public static void main(String[] args) {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        List<Usuario> usuarios = new RepositorioMiembros().obtenerMiembros(entityManager).stream().map(Miembro::getUsuario).toList();
        LocalizacionUpdater.agregarUsuarios(usuarios);
        LocalizacionUpdater.actualizarLocalizaciones(entityManager);
    }
}
