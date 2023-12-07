package domain.models.entities.admins.rankings;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.entidadesDeServicio.Entidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MayorGradoIncidentes extends CriterioRanking{
    public double transformarListaAValor(List<Incidente> incidentes){return Math.random();}
}

