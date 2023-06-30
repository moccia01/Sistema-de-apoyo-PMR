package domain.comunidad;

import domain.Mensajes.Configuraciones.MedioConfigurado;
import domain.Mensajes.Configuraciones.TiempoConfigurado;
import domain.entidadesDeServicio.Entidad;
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

    public void generarIncidente(Comunidad comunidad, Entidad entidad, String descripcion){
        comunidad.generarIncidente(entidad, descripcion);
    }

    public void cerrarIncidente(){

    }
}
