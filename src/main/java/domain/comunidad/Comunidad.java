package domain.comunidad;

import domain.Mensajes.Notificador;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Comunidad {
    private List<Miembro> miembros;
    public List<Incidente> incidentes; //ESTA HECHO PUBLIC SOLO PARA LOS TEST, DEBERIA SER PRIVATE

    public  Comunidad(){
        this.miembros = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }
    public void notificarMiembros(String notificacion){

        miembros.forEach( m -> {
            Notificador.notificar(m, notificacion);
        });
    }

    public void agregarMiembro(Miembro miembro) {
        miembros.add(miembro);
    }

    public void generarIncidente(PrestacionDeServicio prestacionDeServicio, String descripcion){
        Incidente nuevoIncidente = new Incidente(descripcion, prestacionDeServicio);
        nuevoIncidente.setEstado(false);
        nuevoIncidente.setFechaApertura(LocalDateTime.now());
        incidentes.add(nuevoIncidente);
        this.notificarMiembros("Notificacion Generacion de nuevo Incidente");
    }

    public void cerrarIncidente(Incidente incidente){
        incidente.setEstado(true);
        incidente.setFechaCierre(LocalDateTime.now());
        this.notificarMiembros("Notificacion Cierre de Incidente");
    }

    public void recibirLocalizacion(Miembro miembro){
        List<Incidente> incidentesCercanos = incidentes.stream().filter(
                i -> miembro.getUsuario().getLocalizacion().estaCercaDe(i.getLocalizacion()) && i.getEstado()
        ).toList();
        incidentesCercanos.forEach(
                i -> Notificador.notificar(miembro, "Sugerencia revision incidente"));

    }
}
