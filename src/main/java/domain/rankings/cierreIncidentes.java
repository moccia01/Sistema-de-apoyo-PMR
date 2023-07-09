package domain.rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class cierreIncidentes {

    LocalDate fechaComienzoSemana; //se tiene q cambiar cada lunes
    LocalDate fechaFinSemana; //se tiene q cambiar cada domingo
    List<Comunidad> comunidades;
    List<List<Incidente>> listaIncidentes;

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
