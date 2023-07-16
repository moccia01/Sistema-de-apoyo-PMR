package domain.Mensajes.Configuraciones;

import domain.comunidad.Miembro;

import java.util.List;
import java.util.TimerTask;

public class TareaProgramada extends TimerTask {
    private Miembro miembro;
    private List<String> notificacionesPendientes;


    public TareaProgramada(Miembro miembros,List<String> notificaciones) {
        this.miembro = miembros;
        this.notificacionesPendientes = notificaciones;
    }
    @Override
    public void run() {
        enviarNotificacionesPendientes(miembro);
    }

    public void enviarNotificacionesPendientes(Miembro miembro) { // TODO falta testear con whatApp

        notificacionesPendientes.forEach(n -> miembro.getMedioConfigurado().enviarNotificacion(miembro, n));
    }
}
