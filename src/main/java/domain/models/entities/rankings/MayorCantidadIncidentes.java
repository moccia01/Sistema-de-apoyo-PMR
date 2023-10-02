package domain.models.entities.rankings;

import domain.models.entities.comunidad.Incidente;

import java.util.*;

public class MayorCantidadIncidentes extends CriterioRanking {

    public void buscarYEliminarRepetidos(Incidente incidente, List<Incidente> incidentes){
        List<Incidente> copiaIncidentes = new ArrayList<>(incidentes);
        Iterator<Incidente> it = copiaIncidentes.iterator();
        boolean primerIncidente = true;

        while (it.hasNext()) {
            Incidente incidente1 = it.next();

            if (incidente != incidente1 && incidente.estaRepetidoDentroDelPlazo(incidente1)) {
                if (primerIncidente) {
                    primerIncidente = false;
                } else {
                    it.remove();
                }
            }
        }

        incidentes.clear();
        incidentes.addAll(copiaIncidentes);
        }

    public void filtrarRepetidos(List<Incidente> incidentes) {
        Set<Incidente> incidentesUnicos = new HashSet<>();
        List<Incidente> incidentesFiltrados = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            if (incidentesUnicos.add(incidente)) {
                incidentesFiltrados.add(incidente);
            }
        }

        incidentes.clear();
        incidentes.addAll(incidentesFiltrados);
    }

    public double transformarListaAValor(List<Incidente> incidentes) {
        List<Incidente> copiaIncidentes = new ArrayList<>(incidentes);
        this.filtrarRepetidos(copiaIncidentes);
        return incidentes.size();
    }

}
