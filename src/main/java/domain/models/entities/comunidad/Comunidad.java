package domain.models.entities.comunidad;

import domain.models.entities.db.EntidadPersistente;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.mensajes.Notificaciones.AperturaIncidente;
import domain.models.entities.mensajes.Notificaciones.CierreIncidente;
import domain.models.entities.mensajes.Notificaciones.TipoNotificacion;
import domain.models.repositorios.RepositorioIncidentes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column
    private double puntosDeConfianza;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grado_confianza_id")
    private GradoDeConfianza gradoDeConfianza;

    public Comunidad(){
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

    public void generarIncidente(Usuario usuarioApertura, String titulo, PrestacionDeServicio prestacionDeServicio, String descripcion){
        Incidente nuevoIncidente = new Incidente(descripcion, prestacionDeServicio);
        nuevoIncidente.setUsuarioApertura(usuarioApertura);
        nuevoIncidente.setFechaHoraApertura(LocalDateTime.now());
        nuevoIncidente.setTitulo(titulo);
        incidentes.add(nuevoIncidente);
        this.notificarMiembros(nuevoIncidente, new AperturaIncidente());
    }

    public void cerrarIncidente(Incidente incidente){
        incidente.cerrar();
        this.notificarMiembros(incidente, new CierreIncidente());
    }

    public List<Usuario> obtenerUsuarioAPartirDeMiembros(){
        return this.miembros.stream().map(Miembro::getUsuario).toList();
    }
}
