package domain.Mensajes;

import domain.comunidad.Miembro;

public class Notificador {

    public static void notificar(Miembro miembro, String notificacion){
        miembro.getTiempoConfigurado().recibirNotificacion(miembro, notificacion);
    }
}
