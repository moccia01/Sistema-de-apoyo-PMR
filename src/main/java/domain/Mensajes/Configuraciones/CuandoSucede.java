package domain.Mensajes.Configuraciones;

import domain.comunidad.Miembro;

public class CuandoSucede implements TiempoConfigurado{
    @Override
    public void recibirNotificacion(Miembro miembro, String notificacion) {
        miembro.getMedioConfigurado().enviarNotificacion(miembro, notificacion);
    }

    @Override
    public void mandarPendientes(Miembro miembro) {

    }

}
