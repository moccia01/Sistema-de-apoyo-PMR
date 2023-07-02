package domain.Mensajes.Configuraciones.SinApuro;

import domain.Mensajes.Configuraciones.MensajeEmail;
import domain.Mensajes.Configuraciones.TiempoConfigurado;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class Prueba implements TiempoConfigurado {
    private LocalTime horario;
    private List<String> notificacionesPendientes;
    private Usuario usuario = new Usuario();

    public Prueba(LocalTime horario, Miembro miembro,String notificacion) {
        this.horario = horario;
        this.notificacionesPendientes = new ArrayList<>();
        this.iniciarNotificacionProgramada(miembro, notificacion);
    }

    public void iniciarNotificacionProgramada(Miembro miembro,String notificacion) {
        TimerTask notificacionTask = new TimerTask() {
            @Override
            public void run() {
                enviarNotificacionesPendientes(miembro , notificacion);
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(notificacionTask, obtenerFechaInicio(), obtenerIntervalo());
    }

    public void enviarNotificacionesPendientes(Miembro miembro,String notificacion) {
        if (esHoraDeNotificar()) {
            MensajeEmail mensajeEmail = new MensajeEmail();
            for (String notificaciones : notificacionesPendientes) {
                mensajeEmail.enviarNotificacion(miembro, notificaciones);

            }

            notificacionesPendientes.clear();
        }else {
            this.notificacionesPendientes.add(notificacion);
        }
    }

    public boolean esHoraDeNotificar() {
        LocalTime horaActual = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        return horaActual.equals(horario);

    }

    public Date obtenerFechaInicio() {
        LocalDate fechaActual = LocalDate.now();
        return Date.from(fechaActual.atTime(horario).atZone(ZoneId.systemDefault()).toInstant());
    }
    // Intervalo de 24 horas para verificar notificaciones pendientes
    public long obtenerIntervalo() {
        return TimeUnit.DAYS.toMillis(1);
    }



    @Override
    public void recibirNotificacion(Miembro miembro, String notificacion) {

    }
}
