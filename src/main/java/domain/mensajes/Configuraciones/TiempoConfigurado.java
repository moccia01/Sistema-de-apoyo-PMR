package domain.mensajes.Configuraciones;

import domain.comunidad.Usuario;
import domain.db.EntidadPersistente;

import javax.persistence.*;

@Entity(name = "tiempo_configurado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TiempoConfigurado extends EntidadPersistente {

    public abstract void recibirNotificacion(Usuario usuario, String notificacion);

    public abstract void mandarPendientes(Usuario usuario);
}
