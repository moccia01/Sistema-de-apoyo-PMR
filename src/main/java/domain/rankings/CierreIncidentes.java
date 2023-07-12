package domain.rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class CierreIncidentes extends CriterioRanking{

    public double tiempoDeCierre(Incidente incidente){
        return ChronoUnit.HOURS.between(incidente.getFechaCierre(), incidente.getFechaApertura());
    }

    public double promedioTiempoCierre(List<Incidente> incidentes){
        return incidentes.stream().mapToDouble(this::tiempoDeCierre).sum() / incidentes.size();
    }

    @Override
    public boolean incidenteValido(Incidente incidente){
        return super.incidenteValido(incidente) && incidente.getEstado(); // si el estado es true significa que esta resuelto (cerrado)
    }

    public double transformarListaAValor(List<Incidente> incidentes){
        return this.promedioTiempoCierre(incidentes);
    }

/*    @Override //TODO: TESTEARRR!!!!
    public List<Entidad> generarRanking(HashMap<Entidad, List<Incidente>> incidentesPorEntidad) {
        List<Entidad> entidades = new ArrayList<>();
        LinkedHashMap<Entidad, Double> promediosPorEntidad = new LinkedHashMap<>();
        for(Map.Entry<Entidad, List<Incidente>> entry : incidentesPorEntidad.entrySet()){
            promediosPorEntidad.put(entry.getKey(), this.promedioTiempoCierre(entry.getValue()));
        }
        List<Map.Entry<Entidad, Double>> promediosOrdenados = new ArrayList<>(promediosPorEntidad.entrySet());
        promediosOrdenados.sort(Comparator.comparingDouble(Map.Entry::getValue));
        for(Map.Entry<Entidad, Double> entry: promediosOrdenados){
            entidades.add(entry.getKey());
        }
        return entidades;

    }
*/
}
