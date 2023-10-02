package domain.models.entities.mensajes.Configuraciones;

import domain.models.entities.comunidad.Usuario;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("cuando_sucede")
@DiscriminatorColumn(name = "discriminador")
public class CuandoSucede extends TiempoConfigurado{

    @Override
    public void recibirNotificacion(Usuario usuario, String notificacion) {
        usuario.getMedioConfigurado().enviarNotificacion(usuario, notificacion);
    }

    @Override
    public void mandarPendientes(Usuario usuario) {

    }
}
