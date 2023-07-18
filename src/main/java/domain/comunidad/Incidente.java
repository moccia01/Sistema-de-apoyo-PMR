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
    public LocalDate fechaApertura;
    public LocalTime horarioApertura;
    public LocalDate fechaCierre;
    public LocalTime horarioCierre;
    private String descripcion;
    private Boolean estado; // true es si esta cerrado
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
        long diasEntrefec1yfec2 = ChronoUnit.DAYS.between(this.getFechaApertura(), incidente.getFechaApertura());
        if(diasEntrefec1yfec2 == 1){
            int hora1 = this.horarioApertura.getHour();
            int hora2 = incidente.horarioApertura.getHour() + 24;
            return ((hora2 - hora1) <= 24);
        }
        return ChronoUnit.DAYS.between(this.getFechaApertura(), incidente.getFechaApertura()) < 1;
    }

    public boolean estaRepetidoDentroDelPlazo(Incidente incidente){
        return this.esElMismoQueOtro(incidente) && this.estaDentroDeLas24hs(incidente);
    }
}
