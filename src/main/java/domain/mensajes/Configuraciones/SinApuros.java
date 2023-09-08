package domain.mensajes.Configuraciones;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.*;

@Getter
@Setter
public class SinApuros implements TiempoConfigurado {
    private List<LocalTime> horarios;
    private List<String> notificacionesPendientes;

    public SinApuros() {
        this.inicializarNotificacionesPendientes();
        this.horarios = new ArrayList<>();
    }

    public void inicializarNotificacionesPendientes(){
        this.notificacionesPendientes = new ArrayList<>();
    }

    public void agregarHorarios(LocalTime ... horarios){
        Collections.addAll(this.horarios, horarios);
    }

    @Override
    public void recibirNotificacion(Usuario usuario, String notificacion) {
        notificacionesPendientes.add(notificacion);
    }

    @Override
    public void mandarPendientes(Usuario usuario) {
        if(this.esHoradeMandarPendientes()){
            this.notificacionesPendientes.forEach(n -> usuario.getMedioConfigurado().enviarNotificacion(usuario, n));
        }
    }

    public boolean esHoradeMandarPendientes(){
        return horarios.stream().anyMatch(h -> h.equals(LocalTime.now()));
    }








}