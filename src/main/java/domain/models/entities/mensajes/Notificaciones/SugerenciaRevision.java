package domain.models.entities.mensajes.Notificaciones;

import domain.models.entities.comunidad.Incidente;

public class SugerenciaRevision extends TipoNotificacion{

    @Override
    public String armarNotificacion(Incidente incidente) {
        return "Estas cerca de un incidente, te sugerimos que lo revises para saber si fue resuelto: " +
                incidente.armarNotificacion();
    }
}
