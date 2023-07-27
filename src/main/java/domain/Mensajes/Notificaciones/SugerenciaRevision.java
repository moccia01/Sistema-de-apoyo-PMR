package domain.Mensajes.Notificaciones;

import domain.comunidad.Incidente;
import domain.comunidad.Miembro;

public class SugerenciaRevision extends TipoNotificacion{

    @Override
    public String armarNotificacion(Incidente incidente) {
        return "Estas cerca de un incidente, te sugerimos que lo revises para saber si fue resuelto: " +
                incidente.armarNotificacion();
    }
}
