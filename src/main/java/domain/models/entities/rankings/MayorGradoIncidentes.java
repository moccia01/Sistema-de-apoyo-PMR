package domain.models.entities.rankings;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.entidadesDeServicio.Entidad;

import java.util.HashMap;
import java.util.List;

public class MayorGradoIncidentes extends CriterioRanking{
    @Override
    public List<Entidad> generarRanking(HashMap<Entidad, List<Incidente>> incidentesPorEntidad) {
        return null;
    }

    public double transformarListaAValor(List<Incidente> incidentes){return 0.0;}
}

