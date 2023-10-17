package domain.models.entities.rankings;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Getter
@Setter
public abstract class CriterioRanking {
    public LocalDateTime fechaActual = LocalDateTime.now();

    List<Entidad> generarRanking(HashMap<Entidad, List<Incidente>> incidentesPorEntidad) {
        List<Entidad> entidades = new ArrayList<>();
        LinkedHashMap<Entidad, Double> hashMapLinkeado = new LinkedHashMap<>();
        for(Map.Entry<Entidad, List<Incidente>> entry : incidentesPorEntidad.entrySet()){
            hashMapLinkeado.put(entry.getKey(), this.transformarListaAValor(entry.getValue()));
        }
        List<Map.Entry<Entidad, Double>> listaOrdenada = new ArrayList<>(hashMapLinkeado.entrySet());
        listaOrdenada.sort(Comparator.comparingDouble(Map.Entry::getValue));
        for(Map.Entry<Entidad, Double> entry: listaOrdenada){
            entidades.add(entry.getKey());
        }
        return entidades;
    }

    public abstract double transformarListaAValor(List<Incidente> incidentes);

    public boolean incidenteValido(Incidente incidente){
        return this.estaDentroDeLaSemana(incidente.getFechaHoraApertura());
    }

    public boolean estaDentroDeLaSemana(LocalDateTime fecha){
        return fecha.isAfter(fechaActual.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))) && fecha.isBefore(fechaActual.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));
    }


}
