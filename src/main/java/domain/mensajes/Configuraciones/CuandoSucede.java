package domain.mensajes.Configuraciones;

import domain.comunidad.Miembro;
import domain.comunidad.Usuario;

public class CuandoSucede implements TiempoConfigurado{
    @Override
    public void recibirNotificacion(Usuario usuario, String notificacion) {
        usuario.getMedioConfigurado().enviarNotificacion(usuario, notificacion);
    }

    @Override
    public void mandarPendientes(Usuario usuario) {

    }
}
