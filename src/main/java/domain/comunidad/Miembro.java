package domain.comunidad;

import domain.Mensajes.Configuraciones.MedioConfigurado;
import domain.Mensajes.Configuraciones.TiempoConfigurado;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Miembro {
    private Usuario usuario;
    private Rol rol;
    private List<Comunidad> comunidades;
    //private RolTemporal rolTemporal;  TODO ver que onda con el rol temporal del usuario, sino me equivoco, lo pusimos como ah, el miembro se quebró la pierna y está así por 3 meses
    private TiempoConfigurado tiempoConfigurado;
    private MedioConfigurado medioConfigurado;

    public void generarIncidente(PrestacionDeServicio prestacionDeServicio, String descripcion){
        comunidades.forEach(c -> c.generarIncidente(prestacionDeServicio, descripcion));
    }

    public void cerrarIncidente(Comunidad comunidad, Incidente incidente){
        comunidad.cerrarIncidente(incidente);
    }

    public void mandarLocalizacion(){

    }
}
