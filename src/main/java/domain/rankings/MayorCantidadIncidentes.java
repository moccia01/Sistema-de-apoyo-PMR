package domain.rankings;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;

import java.util.HashMap;
import java.util.List;

public class MayorCantidadIncidentes extends CriterioRanking{

    public void removerSiEstaRepetido(Incidente incidente, List<Incidente> incidentes, Incidente incidenteContraElQueComparo){
        if(incidente.estaRepetidoDentroDelPlazo(incidenteContraElQueComparo)){
            incidentes.remove(incidenteContraElQueComparo);
        }
    }

//    public void eliminarRepetidoEn24hs(Incidente incidente, List<Incidente> incidentes, int posAPartirDelIncidente){
//        incidentes.subList(posAPartirDelIncidente, incidentes.size() - 1).forEach(i -> this.removerSiEstaRepetido(incidente, incidentes, i));
//    }

//    public void filtrarRepetidos (List<Incidente> incidentes){
//        incidentes.forEach(i -> this.eliminarRepetidoEn24hs(i, incidentes, incidentes.indexOf(i)));
//    }

    public void filtrarRepetidos(List<Incidente> incidentes){
        for(int i = 0; i < incidentes.size(); i++){
            Incidente elem1 = incidentes.get(i);
            for(int j = i + 1; j < incidentes.size(); j++){
                Incidente elem2 = incidentes.get(j);
                removerSiEstaRepetido(elem1, incidentes, elem2);
            }
        }
    }

    public double transformarListaAValor(List<Incidente> incidentes){
        this.filtrarRepetidos(incidentes);
        return incidentes.size();
    }
}
