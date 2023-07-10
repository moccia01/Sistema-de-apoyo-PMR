package domain.rankings;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;

import java.util.HashMap;
import java.util.List;

public interface CriterioRanking {
    List<Entidad> generarRanking(HashMap<Entidad, List<Incidente>> incidentesPorEntidad);
}
