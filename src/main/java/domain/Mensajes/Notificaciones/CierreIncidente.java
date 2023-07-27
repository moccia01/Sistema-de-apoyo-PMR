package domain.Mensajes.Notificaciones;

import domain.comunidad.Incidente;
import domain.comunidad.Miembro;

public class CierreIncidente extends TipoNotificacion{

    @Override
    public String armarNotificacion(Incidente incidente) {
        return "Hubo un cierre de incidente en " + incidente.armarNotificacion();
    }
}
