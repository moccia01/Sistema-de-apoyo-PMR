package domain.Mensajes.Configuraciones;

import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
@Getter
@Setter
public class SinApuros implements TiempoConfigurado {
    private LocalTime horario;
    private List<String> notificacionesPendientes;
    private Usuario usuario = new Usuario();

    public SinApuros(LocalTime horario,Miembro miembro) {
        this.horario = horario;
        this.notificacionesPendientes = new ArrayList<>();
        this.iniciarNotificacionProgramada(miembro);
    }




    public void iniciarNotificacionProgramada(Miembro miembro) {
        TimerTask notificacionTask = new TimerTask() {
            @Override
            public void run() {
                enviarNotificacionesPendientes(miembro);
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(notificacionTask, obtenerFechaInicio(), obtenerIntervalo());
    }
    @Override
    public void recibirNotificacion(Miembro miembro, String notificacion) {
        if (!esHoraDeNotificar()) {
            notificacionesPendientes.add(notificacion);
        }
    }
    public void enviarNotificacionesPendientes(Miembro miembro) {
        if (this.esHoraDeNotificar()) {
            MensajeEmail mensajeEmail = new MensajeEmail();

            for (String notificacion : notificacionesPendientes) {
                mensajeEmail.enviarNotificacion(miembro, notificacion);
            }

            notificacionesPendientes.clear();
        }

    }

    public boolean esHoraDeNotificar() {
        LocalTime horaActual = LocalTime.now();
        return horaActual.equals(horario);
    }

    public Date obtenerFechaInicio() {
        LocalDate fechaActual = LocalDate.now();
        return Date.from(fechaActual.atTime(horario).atZone(ZoneId.systemDefault()).toInstant());
    }

    public long obtenerIntervalo() {
        return TimeUnit.DAYS.toMillis(1); // Intervalo de 24 horas para verificar notificaciones pendientes
    }

    public void notificarCuandoSeaElHorario(Miembro miembro) {
        LocalTime horaActual = LocalTime.now();
        if (horaActual.equals(horario)) {
            // El horario coincide, enviar todas las notificaciones pendientes
            for (String notificacion : notificacionesPendientes) {
                // Enviar la notificación por correo electrónico al miembro
                MensajeEmail mensajeEmail = new MensajeEmail();
                mensajeEmail.enviarNotificacion(miembro, notificacion);
            }
            // Limpiar la lista de notificaciones pendientes
            notificacionesPendientes.clear();
        }
    }

}
