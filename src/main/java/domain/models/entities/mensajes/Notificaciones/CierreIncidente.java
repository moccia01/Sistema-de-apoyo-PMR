package domain.models.entities.mensajes.Notificaciones;

import domain.models.entities.comunidad.Incidente;

public class CierreIncidente extends TipoNotificacion{

    @Override
    public String armarNotificacion(Incidente incidente) {
        return "Hubo un cierre de incidente en " + incidente.armarNotificacion();
    }
}
