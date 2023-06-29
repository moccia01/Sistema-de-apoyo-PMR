package domain.comunidad;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Comunidad {
    private List<Miembro> miembros;
    private List<Incidente> incidentes;

    public void notificarMiembros(){

    }

    public void generarIncidente(){

    }

    public void cerrarIncideten(){

    }
    //Faltan cosas
}
