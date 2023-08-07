package domain.localizacion;

import domain.Mensajes.Notificaciones.SugerenciaRevision;
import domain.comunidad.Incidente;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import domain.rankings.RepositorioComunidades;

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

    public static void actualizarLocalizaciones(){
        usuarios.forEach(u -> u.setLocalizacion(sistemaPosicionamiento.getPosicion(u.getNumero())));
        RepositorioComunidades.obtenerMiembros().forEach(LocalizacionUpdater::verificarIncidentesCercanos);
    }

    public static void verificarIncidentesCercanos(Miembro miembro){
        List<Incidente> incidentesCercanosEInteresantes = miembro.getComunidades()
                .stream().flatMap(c -> c.getIncidentes().stream())
                .filter(i -> miembro.estaInteresadoEn(i) && miembro.estaCercaDe(i))
                .toList();

        incidentesCercanosEInteresantes.forEach(i -> new SugerenciaRevision().notificar(miembro, i));
    }


    public static void main(String[] args) {
        List<Usuario> usuarios = RepositorioComunidades.obtenerMiembros().stream().map(Miembro::getUsuario).toList();
        LocalizacionUpdater.agregarUsuarios(usuarios);
        LocalizacionUpdater.actualizarLocalizaciones();
    }
}
