package domain.models.entities.admins.rankings;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.entidadesDeServicio.Entidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MayorGradoIncidentes extends CriterioRanking{
    @Override
    public List<Entidad> generarRanking(HashMap<Entidad, List<Incidente>> incidentesPorEntidad) {
        //TODO falla porque retorna null pero no hay especificacion sobre este criterio todavia
        return new ArrayList<>();
    }

    public double transformarListaAValor(List<Incidente> incidentes){return 0.0;}
}

