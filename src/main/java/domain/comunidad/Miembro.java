package domain.comunidad;

import domain.Mensajes.Configuraciones.MedioConfigurado;
import domain.Mensajes.Configuraciones.TiempoConfigurado;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Setter
@Getter
public class Miembro {
    private Usuario usuario;
    private Rol rol;
    private List<Comunidad> comunidades;
    private RolTemporal rolTemporal;
    private TiempoConfigurado tiempoConfigurado;
    private MedioConfigurado medioConfigurado;
    private final long refrescoLocalizacion = 5000; // en milisegundos

    public Miembro(Usuario usuario, Rol rol) {
        this.usuario = usuario;
        this.rol = rol;
        this.comunidades = new ArrayList<>();
    }

    public void agregarComunidad(Comunidad comunidad){
        comunidades.add(comunidad);
        if(comunidades.size() == 1){
            this.iniciarTimer();
        }
    }

    public void generarIncidente(PrestacionDeServicio prestacionDeServicio, String descripcion){
        comunidades.forEach(c -> c.generarIncidente(prestacionDeServicio, descripcion));
    }

    public void cerrarIncidente(Comunidad comunidad, Incidente incidente){
        comunidad.cerrarIncidente(incidente);
    }

    public void iniciarTimer(){ //TODO adaptar a la estructura de facu
        TimerTask localizacionTask = new TimerTask() {
            @Override
            public void run(){
                mandarLocalizacion();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(localizacionTask, 0, refrescoLocalizacion);
    }

    public void mandarLocalizacion(){
        comunidades.forEach(c -> c.recibirLocalizacion(this));
    }

    public void cambiarRolTemporal(RolTemporal nuevoRol) {
        this.rolTemporal = nuevoRol;
    }
}
