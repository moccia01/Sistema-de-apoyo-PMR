package domain.rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioIncidentes {
    private static List<Comunidad> comunidades;

    public RepositorioIncidentes() {
        this.comunidades = new ArrayList<>();
    }

    public void agregarComunidades(Comunidad ... comunidades){
        Collections.addAll(this.comunidades, comunidades);
    }

    public static List<Incidente> obtenerIncidentesDeComunidades(){
        List<List<Incidente>> listaIncidentes = new ArrayList<>();

        for(Comunidad comunidad : comunidades){
            listaIncidentes.add(comunidad.getIncidentes());
        }
        return listaIncidentes.stream().flatMap(List::stream).collect(Collectors.toList());
    }
}
