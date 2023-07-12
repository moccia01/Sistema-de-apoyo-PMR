package domain.rankings;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class GeneradorDeRankings {
    List<Incidente> incidentes;

    public GeneradorDeRankings() {
        this.incidentes = RepositorioIncidentes.obtenerIncidentesDeComunidades();
    }

    public List<Entidad> generarSegunCriterio(CriterioRanking criterio){
        if(LocalDate.now().isAfter(criterio.getFechaFinSemana())){
            criterio.cambiarFechas();
        }
        List<Incidente> incidentesValidos = incidentes.stream().filter(criterio::incidenteValido).toList();
        HashMap<Entidad, List<Incidente>> incidentesPorEntidad = new HashMap<>();
        for(Incidente incidente : incidentes){
            Entidad entidad = incidente.getPrestacionDeServicio().getEntidad();
            incidentesPorEntidad.put(entidad, this.obtenerIncidentesDeEntidad(entidad, incidentesValidos));
        }
        return criterio.generarRanking(incidentesPorEntidad);
    }

    public List<Incidente> obtenerIncidentesDeEntidad(Entidad entidad, List<Incidente> incidentes){
        return incidentes.stream().filter(i -> i.getPrestacionDeServicio().getEntidad() == entidad).toList();
    }

}
