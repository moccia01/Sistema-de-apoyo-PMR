package domain.comunidad;

import domain.Mensajes.Configuraciones.MedioConfigurado;
import domain.Mensajes.Configuraciones.TiempoConfigurado;
import domain.entidadesDeServicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@Setter
@Getter
public class Miembro {
    private Usuario usuario;
    private Rol rol;
    private List<Comunidad> comunidades;
    private RolTemporal rolTemporal;
    private TiempoConfigurado tiempoConfigurado;
    private MedioConfigurado medioConfigurado;
    private Interes interes;
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

    public void iniciarTimer(){
        Timer timer = new Timer();
        LocalizacionTimer localizacionTask = new LocalizacionTimer(comunidades,this);
        timer.scheduleAtFixedRate(localizacionTask, 0, refrescoLocalizacion);
    }

    public boolean estaInteresadoEn(Incidente incidente){
        return this.interes.contieneEntidad(incidente.getPrestacionDeServicio().getEntidad()) &&
                this.interes.contieneServicio(incidente.getPrestacionDeServicio().getServicio());
    }

    public void cambiarRolTemporal(RolTemporal nuevoRol) {
        this.rolTemporal = nuevoRol;
    }

    public void mandarPendientes(){
        this.tiempoConfigurado.mandarPendientes(this);
    }

    public boolean estaCercaDe(Incidente incidente){
        return this.usuario.getLocalizacion().estaCercaDe(incidente.getLocalizacion());
    }

}
