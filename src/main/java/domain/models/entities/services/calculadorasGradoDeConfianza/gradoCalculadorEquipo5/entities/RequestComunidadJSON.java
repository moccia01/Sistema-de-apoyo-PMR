package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;

import java.util.List;

public class RequestComunidadJSON {
    public ComunidadApi5 comunidad;
    public List<IncidenteApi5> incidentes;

    public void cargar(Comunidad comunidad , List<Incidente> incidentes){
        //this.comunidad.cargar(comunidad,);
    }

}
