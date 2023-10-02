package domain.models.entities.comunidad;

import domain.models.entities.db.EntidadPersistente;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.mensajes.Notificaciones.AperturaIncidente;
import domain.models.entities.mensajes.Notificaciones.CierreIncidente;
import domain.models.entities.mensajes.Notificaciones.TipoNotificacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comunidad")
public class Comunidad extends EntidadPersistente {

    @Column
    private String nombre;

    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL)
    private List<Miembro> miembros;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Incidente> incidentes;

    public  Comunidad(){
        this.miembros = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }
    public void notificarMiembros(Incidente incidente, TipoNotificacion notificacion){

        miembros.stream().filter(m -> m.getUsuario().estaInteresadoEn(incidente)).forEach( m -> {
            notificacion.notificar(m.getUsuario(), incidente);
        });
    }

    public void agregarMiembros(Miembro ... miembros){
        Collections.addAll(this.miembros, miembros);
    }

    public void generarIncidente(PrestacionDeServicio prestacionDeServicio, String descripcion){
        Incidente nuevoIncidente = new Incidente(descripcion, prestacionDeServicio);
        incidentes.add(nuevoIncidente);
        this.notificarMiembros(nuevoIncidente, new AperturaIncidente());
    }

    public void cerrarIncidente(Incidente incidente){
        incidente.cerrar();
        this.notificarMiembros(incidente, new CierreIncidente());
    }
}
