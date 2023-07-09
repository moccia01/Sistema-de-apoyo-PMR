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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class cierreIncidentes {

    public LocalDate fechaComienzoSemana; //se tiene q cambiar cada lunes
    public LocalDate fechaFinSemana; //se tiene q cambiar cada domingo
    private List<Comunidad> comunidades;
    private List<List<Incidente>> listaIncidentes;

    public void cambiarFechaInicioSemana(LocalDate fecha){
        if(fecha.isAfter(fechaComienzoSemana) && fecha.isAfter(fechaFinSemana) && fecha.getDayOfWeek() == DayOfWeek.MONDAY){
            fechaComienzoSemana = fecha;
        }
    }

    public void cambiarFechaFinSemana(LocalDate fecha){
        if(fecha.isAfter(fechaComienzoSemana) && fecha.isAfter(fechaFinSemana) && fecha.getDayOfWeek() == DayOfWeek.SUNDAY){
            fechaFinSemana = fecha;
        }
    }

    // ver como hacer para que lo de arriba no quede con codigo repetido

    public List<Incidente> obtenerIncidentesDeComunidades(List<Comunidad> comunidades, List<List<Incidente>> listaIncidentes){
        for(Comunidad comunidad : comunidades){
            listaIncidentes.add(comunidad.getIncidentes());
        }
        return listaIncidentes.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public boolean estaDentroDeLaSemana(LocalDate fecha){
        return ChronoUnit.DAYS.between(fecha, fechaComienzoSemana) <= 7 && ChronoUnit.DAYS.between(fecha, fechaFinSemana) <= 7;
    }

    public List<Incidente> filtarPorSemana(List<Incidente> listaIncidentes){
        for(Incidente incidente : listaIncidentes){
            if(!estaDentroDeLaSemana(incidente.getFechaApertura())){
                listaIncidentes.remove(incidente);
            }
        }
        return listaIncidentes;
    }


    public HashMap<Entidad, Double> calcularRanking(){
        HashMap<Entidad, Double> ranking = new HashMap<>();
        return ranking;
    }

}
