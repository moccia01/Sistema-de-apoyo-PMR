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
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Incidente {
    public LocalDate fechaApertura;
    public LocalDate fechaCierre;
    private String descripcion;
    private Boolean estado;
    private PrestacionDeServicio prestacionDeServicio;

    public Localizacion getLocalizacion(){
        return prestacionDeServicio.getEstablecimiento().getLocalizacion();
    }

    public Incidente(String descripcion, PrestacionDeServicio prestacion) {
        this.descripcion = descripcion;
        this.prestacionDeServicio = prestacion;
    }

    public boolean esElMismoQueOtro(Incidente incidente){
        return this.prestacionDeServicio.esLaMismaQue(incidente.getPrestacionDeServicio());
    }

    public boolean estaDentroDeLas24hs(Incidente incidente){
        return ChronoUnit.HOURS.between(this.getFechaApertura(), incidente.getFechaApertura()) <= 24;
    }

    public boolean estaRepetidoDentroDelPlazo(Incidente incidente){
        return !this.getEstado() && this.esElMismoQueOtro(incidente) && this.estaDentroDeLas24hs(incidente);
    }
}
