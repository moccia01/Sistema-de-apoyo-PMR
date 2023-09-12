package domain.comunidad;

import domain.converters.LocalDateAttributeConverter;
import domain.converters.LocalTimeAttributeConverter;
import domain.db.EntidadPersistente;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Table(name = "incidente")
public class Incidente extends EntidadPersistente {
    @Column
    @Convert(converter = LocalDateAttributeConverter.class)         //TODO Convertir a localDateTime con AttributeConverter
    public LocalDate fechaApertura;

    @Column
    @Convert(converter = LocalTimeAttributeConverter.class)         //TODO Convertir a localDateTime con AttributeConverter
    public LocalTime horarioApertura;

    @Column
    @Convert(converter = LocalTimeAttributeConverter.class)
    public LocalDate fechaCierre;

    @Column
    @Convert(converter = LocalTimeAttributeConverter.class)
    public LocalTime horarioCierre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "BOOL")
    private Boolean estado; // true es si esta cerrado

    @Embedded
    private PrestacionDeServicio prestacionDeServicio;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Usuario usuarioApertura;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Usuario usuarioCierre;

    public Incidente() {

    }

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

    public boolean estaDentroDeLas24hs(Incidente incidente) {
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaIncidente = incidente.getFechaApertura().atTime(incidente.getHorarioApertura());

        long horasDiferencia = ChronoUnit.HOURS.between(fechaIncidente, fechaActual);


        return horasDiferencia >= 0 && horasDiferencia <= 24;
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
        this.fechaApertura = LocalDate.now();
        this.horarioApertura = LocalTime.now();
    }

    public void cerrar(){
        this.estado = true;
        this.fechaCierre = LocalDate.now();
        this.horarioCierre = LocalTime.now();
    }
}
