package domain.models.entities.mensajes.Configuraciones;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.converters.LocalDateTimeAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.*;

@Getter
@Setter
@Entity
@DiscriminatorValue("sin_apuros")
@DiscriminatorColumn(name = "discriminador")
public class SinApuros extends TiempoConfigurado {

    @ElementCollection
    @CollectionTable(name = "sin_apuros_horarios", joinColumns = @JoinColumn(name = "sin_apuros_id"))
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "horarios")
    private List<LocalTime> horarios;

    @ElementCollection
    @CollectionTable(name = "sin_apuros_notificaciones_pendientes", joinColumns = @JoinColumn(name = "sin_apuros_id"))
    @Column(name = "notificacion")
    private List<String> notificacionesPendientes;

    public SinApuros(){
        this.inicializarNotificacionesPendientes();
        this.horarios = new ArrayList<>();
    }

    public void inicializarNotificacionesPendientes(){
        this.notificacionesPendientes = new ArrayList<>();
    }

    public void agregarHorarios(LocalTime ... horarios){
        Collections.addAll(this.horarios, horarios);
    }

    @Override
    public void recibirNotificacion(Usuario usuario, String notificacion) {
        notificacionesPendientes.add(notificacion);
    }

    @Override
    public void mandarPendientes(Usuario usuario) {
        if(this.esHoradeMandarPendientes()){
            this.notificacionesPendientes.forEach(n -> usuario.getMedioConfigurado().enviarNotificacion(usuario, n));
        }
    }

    public boolean esHoradeMandarPendientes(){
        return horarios.stream().anyMatch(h -> h.equals(LocalTime.now()));
    }








}