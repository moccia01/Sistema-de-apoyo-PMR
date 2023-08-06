package domain.rankings;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;

import java.util.HashMap;
import java.util.HashSet;
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
    public void filtrarRepetidos2(List<Incidente> incidentes){
        for (int i = 0; i < incidentes.size(); i++){
            Incidente elem1 = incidentes.get(i);
            incidentes.forEach( j -> removerSiEstaRepetido(elem1,incidentes,j));
        }
    }

    public double transformarListaAValor(List<Incidente> incidentes){
        this.filtrarRepetidos(incidentes);
        return incidentes.size();
    }
}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FiltrarRepetidos {

    public static <T> List<T> filtrarRepetidos(List<T> lista) {
        HashSet<T> conjunto = new HashSet<>();
        List<T> resultado = new ArrayList<>();

        for (T elemento : lista) {
            if (conjunto.add(elemento)) {
                resultado.add(elemento);
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        List<Integer> listaNumeros = new ArrayList<>();
        listaNumeros.add(1);
        listaNumeros.add(2);
        listaNumeros.add(3);
        listaNumeros.add(2);
        listaNumeros.add(4);
        listaNumeros.add(1);

        List<Integer> listaFiltrada = filtrarRepetidos(listaNumeros);
        System.out.println("Lista original: " + listaNumeros);
        System.out.println("Lista filtrada: " + listaFiltrada);
    }
}