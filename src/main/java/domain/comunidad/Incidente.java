package domain.comunidad;

import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Incidente {
    public LocalDateTime fechaApertura;
    public LocalDateTime fechaCierre;
    private String descripcion;
    private Boolean estado; // true es si esta cerrado
    private PrestacionDeServicio prestacionDeServicio;

    public Localizacion getLocalizacion(){
        return prestacionDeServicio.getEstablecimiento().getLocalizacion();
    }

    public Incidente(String descripcion, PrestacionDeServicio prestacion) {
        this.descripcion = descripcion;
        this.prestacionDeServicio = prestacion;
        this.abrir();
    }

    public boolean esElMismoQueOtro(Incidente incidente){
        return this.prestacionDeServicio.esLaMismaQue(incidente.getPrestacionDeServicio());
    }

    public boolean estaDentroDeLas24hs(Incidente incidente){
        return Math.abs(ChronoUnit.HOURS.between(this.getFechaApertura(), incidente.getFechaApertura())) <= 24;
    }

    public boolean estaRepetidoDentroDelPlazo(Incidente incidente){
        return this.esElMismoQueOtro(incidente) && this.estaDentroDeLas24hs(incidente);
    }

    public String armarNotificacion(){
        return this.prestacionDeServicio.getEntidad().getNombre() +
               " - " + this.prestacionDeServicio.getEstablecimiento().getNombre() +
                " - " + this.prestacionDeServicio.getServicio().getNombre() +
                ". Descripcion: " + this.getDescripcion();
    }

    public void abrir(){
        this.estado = false;
        this.fechaApertura = LocalDateTime.now();
    }

    public void cerrar(){
        this.estado = true;
        this.fechaCierre = LocalDateTime.now();
    }
}
