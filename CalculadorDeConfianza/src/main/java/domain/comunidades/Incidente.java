package domain.comunidades;

import domain.entidadesDeServicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Incidente {
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private Usuario usuarioApertura;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private Usuario usuarioCierre;
    private String descripcion;
    private Boolean estado; // true es si esta cerrado
    private PrestacionDeServicio prestacionDeServicio;


    public Boolean estaDentroDeLaSemana(){
        return ChronoUnit.DAYS.between(this.fechaApertura, LocalDate.now()) < 7;
    }

    public Boolean esSimilarA(Incidente incidente){
        return this.prestacionDeServicio.esLaMismaQue(incidente.getPrestacionDeServicio());
    }

    public boolean estaCerrado() {
        return !this.estado;
    }

    public boolean tieneDiferenciaDe3MinutosCon(Incidente incidenteCerrado) {
        return ChronoUnit.MINUTES.between(incidenteCerrado.getFechaCierre(), this.fechaApertura) < 3;
    }

    public long minutosVigente(){
        return ChronoUnit.MINUTES.between(this.horarioApertura, this.horarioCierre);
    }
}
