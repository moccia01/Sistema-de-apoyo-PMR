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

    public RepositorioComunidades() {
        comunidades = new ArrayList<>();
    }

    public void agregarComunidades(Comunidad ... comunidades){
        Collections.addAll(RepositorioComunidades.comunidades, comunidades);
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
        comunidades.forEach(c -> miembros.addAll(c.getMiembros()));
        return miembros;
    }
}
