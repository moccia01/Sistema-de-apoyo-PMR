package domain.Mensajes.Configuraciones;

import domain.comunidad.Miembro;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SinApuros implements TiempoConfigurado{
    private LocalTime horario;
    private List<String> notificacionesPendientes;

    public SinApuros(List<String> notificacionesPendientes) {
        this.notificacionesPendientes = new ArrayList<>();
    }

    @Override
    public void recibirNotificacion(Miembro miembro, String notificacion) {
        notificacionesPendientes.add(notificacion);
    }

    public void notificarCuandoSeaElHorario(){

    }
}
