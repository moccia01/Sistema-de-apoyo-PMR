package domain.models.entities.comunidad;

import domain.models.entities.converters.LocalDateTimeAttributeConverter;
import domain.models.entities.db.EntidadPersistente;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Table(name = "incidente")
public class Incidente extends EntidadPersistente {
    //TODO ponerle un titulo al incidente para mostrar en la vista (?)
    @Transient
    private String titulo;


    @Column
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    public LocalDateTime fechaHoraApertura;

    @Column
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    public LocalDateTime fechaHoraCierre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "BOOL")
    private Boolean estado; // true es si esta cerrado

    @Embedded
    private PrestacionDeServicio prestacionDeServicio;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuarioApertura;

    @OneToOne(cascade = CascadeType.ALL)
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
        LocalDateTime fechaIncidente = incidente.getFechaHoraApertura();

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
        this.fechaHoraApertura = LocalDateTime.now();
    }

    public void cerrar(){
        this.estado = true;
        this.fechaHoraCierre = LocalDateTime.now();
    }

    public String estadoToString() {
        if(this.estado){
            return "Cerrado";
        }
        return "Abierto";
    }

    public String usuarioCierreToString
    public String fechaAperturaToString() {
        return this.fechaHoraApertura.toLocalDate().toString();
    }

    public String fechaCierreToString() {
        if (this.fechaHoraCierre == null) {
            return " - ";
        }
        return this.fechaHoraCierre.toLocalDate().toString();
    }
}
