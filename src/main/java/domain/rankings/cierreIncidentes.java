package domain.rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;
import org.quartz.impl.calendar.WeeklyCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class cierreIncidentes implements CriterioRanking{

    /*public void cambiarFechaInicioSemana(LocalDate fecha){
        if(fecha.isAfter(fechaComienzoSemana) && fecha.isAfter(fechaFinSemana) && fecha.getDayOfWeek() == DayOfWeek.MONDAY){
            fechaComienzoSemana = fecha;
        }
    }

    public void cambiarFechaFinSemana(LocalDate fecha){
        if(fecha.isAfter(fechaComienzoSemana) && fecha.isAfter(fechaFinSemana) && fecha.getDayOfWeek() == DayOfWeek.SUNDAY){
            fechaFinSemana = fecha;
        }
    }*/
    // TODO chusmear como esta logica les puede servir aca: LocalDate nuevaFecha = fecha.plusDays(7);
    // ver como hacer para que lo de arriba no quede con codigo repetido

    // TODO esto tiene sentido que este en otra clase por temas de cohesion
    public List<Incidente> obtenerIncidentesDeComunidades(List<Comunidad> comunidades, List<List<Incidente>> listaIncidentes){
        for(Comunidad comunidad : comunidades){
            listaIncidentes.add(comunidad.getIncidentes());
        }
        return listaIncidentes.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public HashMap<Entidad, Double> calcularRanking(){
        HashMap<Entidad, Double> ranking = new HashMap<>();
        return ranking;
    }

    public double tiempoDeCierre(Incidente incidente){
        return ChronoUnit.HOURS.between(incidente.getFechaCierre(), incidente.getFechaApertura());
    }

    public double promedioTiempoCierre(List<Incidente> incidentes){
        return incidentes.stream().mapToDouble(this::tiempoDeCierre).sum() / incidentes.size();
    }

    public List<Incidente> incidentesAbiertos(List<Incidente> incidentes){
        return incidentes.stream().filter(Incidente::getEstado).toList();
    }

    @Override // TODO falta testear
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
}
