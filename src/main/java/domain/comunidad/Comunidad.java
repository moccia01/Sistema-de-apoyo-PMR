package domain.comunidad;

import domain.mensajes.Notificaciones.*;
import domain.entidadesDeServicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Comunidad {
    private List<Miembro> miembros;
    private List<Incidente> incidentes;

    public  Comunidad(){
        this.miembros = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }
    public void notificarMiembros(Incidente incidente, TipoNotificacion notificacion){

        miembros.stream().filter(m -> m.estaInteresadoEn(incidente)).forEach( m -> {
            notificacion.notificar(m, incidente);
        });
    }

    public void agregarMiembro(Miembro miembro) {
        miembros.add(miembro);
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
