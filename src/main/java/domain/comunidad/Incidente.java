package domain.comunidad;

import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
