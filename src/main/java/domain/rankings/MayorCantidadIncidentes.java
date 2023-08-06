package domain.rankings;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MayorCantidadIncidentes extends CriterioRanking {
    //De esta manera funciona 100%
/*
    public void removerSiEstaRepetido(Incidente incidente, List<Incidente> incidentes, Incidente incidenteContraElQueComparo) {
        if (incidente.estaRepetidoDentroDelPlazo(incidenteContraElQueComparo)) {
            incidentes.remove(incidenteContraElQueComparo);
        }
    }

    public void filtrarRepetidos(List<Incidente> incidentes){
        for(int i = 0; i < incidentes.size(); i++){
            Incidente elem1 = incidentes.get(i);
            for(int j = i + 1; j < incidentes.size(); j++){
                Incidente elem2 = incidentes.get(j);
                removerSiEstaRepetido(elem1, incidentes, elem2);
            }
        }
    }

        public void filtrarRepetidos2(List<Incidente> incidentes){
            for (int i = 0; i < incidentes.size(); i++){
                Incidente elem1 = incidentes.get(i);
                incidentes.forEach( j -> removerSiEstaRepetido(elem1,incidentes,j));
            }
        }

    public void filtrarRepetidos(List<Incidente> incidentes) {
        incidentes.removeIf(incidente -> tieneRepetidos(incidente, incidentes));
    }

    public boolean tieneRepetidos(Incidente incidente, List<Incidente> incidentes) {
        return incidentes.stream()
                .filter(i -> !i.equals(incidente)) // Filtrar incidentes diferentes al actual
                .anyMatch(i -> i.estaRepetidoDentroDelPlazo(incidente));
    }
*/

    public void filtrarRepetidos(List<Incidente> incidentes){
        List<Incidente> cpyIncidentes = incidentes;
        cpyIncidentes.removeIf(incidente -> tieneRepetidos(incidente, incidentes));
        System.out.println(incidentes);
    }

    public boolean tieneRepetidos(Incidente incidente, List<Incidente> incidentes) {
        return incidentes.stream()
                .filter(i -> !i.equals(incidente)) // Filtrar incidentes diferentes al actual
                .anyMatch(i -> i.estaRepetidoDentroDelPlazo(incidente));
    }

    public double transformarListaAValor(List<Incidente> incidentes) {
        this.filtrarRepetidos(incidentes);
        return incidentes.size();
    }
}
