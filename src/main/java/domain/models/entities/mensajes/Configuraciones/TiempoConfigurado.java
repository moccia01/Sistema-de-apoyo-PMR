package domain.models.entities.mensajes.Configuraciones;

import domain.models.entities.comunidad.Usuario;
import domain.models.entities.db.EntidadPersistente;

import javax.persistence.*;

@Entity(name = "tiempo_configurado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name = "discriminador")
public abstract class TiempoConfigurado extends EntidadPersistente {

    public abstract void recibirNotificacion(Usuario usuario, String notificacion);

    public abstract void mandarPendientes(Usuario usuario);

    public abstract String discriminador();
}
