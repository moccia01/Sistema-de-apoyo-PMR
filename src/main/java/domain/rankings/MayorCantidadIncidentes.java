package domain.rankings;

import domain.comunidad.Incidente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MayorCantidadIncidentes extends CriterioRanking {

    public void buscarYEliminarRepetidos(Incidente incidente, List<Incidente> incidentes){
        Iterator<Incidente> it = incidentes.iterator();
        boolean primerIncidente = true;

        while(it.hasNext()){
            Incidente incidente1 = it.next();

            if(incidente.estaRepetidoDentroDelPlazo(incidente1)){
                if (primerIncidente) {
                    primerIncidente = false;
                } else {
                    it.remove();
                }

            }
        }

    }

    public void filtrarRepetidos(List<Incidente> incidentes){
        List<Incidente> copiaIncidentes = new ArrayList<>();
        copiaIncidentes.addAll(incidentes);

        copiaIncidentes.forEach(incidente -> buscarYEliminarRepetidos(incidente, incidentes));
    }

    public double transformarListaAValor(List<Incidente> incidentes) {
        this.filtrarRepetidos(incidentes);
        return incidentes.size();
    }

}
