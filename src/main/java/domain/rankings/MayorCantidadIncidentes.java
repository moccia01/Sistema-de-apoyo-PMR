package domain.rankings;

import domain.comunidad.Incidente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MayorCantidadIncidentes extends CriterioRanking {
    /*
        public void removerSiEstaRepetido(Incidente incidente, List<Incidente> incidentes, Iterator<Incidente> it) {
            if (incidente.estaRepetidoDentroDelPlazo(it)) {
                it.remove();
            }
        }
        public void filtrarRepetidos(List<Incidente> incidentes) {
            for (int i = 0; i < incidentes.size(); i++) {
                Incidente elem1 = incidentes.get(i);
                for (int j = i + 1; j < incidentes.size(); j++) {
                    Incidente elem2 = incidentes.get(j);
                    removerSiEstaRepetido(elem1, incidentes, elem2);
                }
            }
        }
    */
    public void buscarYEliminarRepetidos(Incidente incidente, List<Incidente> incidentes){
        List<Incidente> aux = new ArrayList<>();
        Iterator<Incidente> it = incidentes.iterator();

        while(it.hasNext()){
            Incidente incidente1 = it.next();

            if(incidente.estaRepetidoDentroDelPlazo(incidente1)){
                aux.add(incidente1);

                it.remove();


            }
        }

    }


    public void filtrarRepetidos(List<Incidente> incidentes) {


    }

    public double transformarListaAValor(List<Incidente> incidentes) {
        this.filtrarRepetidos(incidentes);
        return incidentes.size();
    }

}
