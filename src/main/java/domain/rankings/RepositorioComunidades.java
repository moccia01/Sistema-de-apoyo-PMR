package domain.rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.comunidad.Miembro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioComunidades {
    private static List<Comunidad> comunidades;

    public static void agregarComunidades(Comunidad ... comunidadesList){
        comunidades = new ArrayList<>();
        Collections.addAll(RepositorioComunidades.comunidades, comunidadesList);
    }

    public static List<Incidente> obtenerIncidentesDeComunidades(){
        List<List<Incidente>> listaIncidentes = new ArrayList<>();

        for(Comunidad comunidad : comunidades){
            listaIncidentes.add(comunidad.getIncidentes());
        }
        return listaIncidentes.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public static List<Miembro> obtenerMiembros(){
        List<Miembro> miembros = new ArrayList<>();
        comunidades.forEach(c -> RepositorioComunidades.agregarMiembros(miembros, c.getMiembros()));
        return miembros;
    }

    public static void agregarMiembros(List<Miembro> miembros, List<Miembro> miembrosDeComunidad){
        miembrosDeComunidad.forEach(m -> RepositorioComunidades.agregarMiembroSiNoEsta(miembros, m));
    }

    public static void agregarMiembroSiNoEsta(List<Miembro> miembros, Miembro miembro){
        if(!miembros.contains(miembro)){
            miembros.add(miembro);
        }
    }
}
