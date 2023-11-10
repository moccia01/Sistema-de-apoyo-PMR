package domain.models.entities.admins.rankings;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.repositorios.RepositorioIncidentes;
import domain.server.Server;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class GeneradorDeRankings {
    private List<Incidente> incidentes;

    public GeneradorDeRankings(RepositorioIncidentes repositorioIncidentes) {
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        this.incidentes = repositorioIncidentes.obtenerIncidentes(entityManager);
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
