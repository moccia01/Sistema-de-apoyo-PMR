package domain.rankings;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import domain.repositorios.RepositorioComunidades;
import domain.repositorios.RepositorioIncidentes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class GeneradorDeRankings {
    List<Incidente> incidentes;

    public GeneradorDeRankings(RepositorioIncidentes repositorioIncidentes) {
        this.incidentes =repositorioIncidentes.obtenerIncidentes();
    }

    public List<Entidad> generarSegunCriterio(CriterioRanking criterio){

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
