package domain.comunidades;

import domain.converters.LocalDateAttributeConverter;
import domain.converters.LocalTimeAttributeConverter;
import domain.db.EntidadPersistente;
import domain.entidadesDeServicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Table(name = "incidente")
public class Incidente extends EntidadPersistente{

    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fechaApertura;

    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fechaCierre;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Usuario usuarioApertura;

    @Column
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime horarioApertura;

    @Column
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime horarioCierre;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Usuario usuarioCierre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "BOOL")
    private Boolean estado; // true es si esta cerrado

    @Embedded
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
