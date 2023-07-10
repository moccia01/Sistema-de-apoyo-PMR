package domain.rankings;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class GeneradorDeRankings {
    public LocalDate fechaComienzoSemana; //se tiene q cambiar cada lunes
    public LocalDate fechaFinSemana; //se tiene q cambiar cada domingo
    List<Incidente> incidentes;

    public List<Entidad> generarSegunCriterio(CriterioRanking criterio){
        if(LocalDate.now().isAfter(fechaFinSemana)){
            this.cambiarFechas();
        }
        List<Incidente> incidentesValidos = incidentes.stream().filter(i -> this.estaDentroDeLaSemana(i.getFechaApertura())).toList();
        HashMap<Entidad, List<Incidente>> incidentesPorEntidad = new HashMap<>();
        for(Incidente incidente : incidentes){
            Entidad entidad = incidente.getPrestacionDeServicio().getEntidad();
            incidentesPorEntidad.put(entidad, this.obtenerIncidentesDeEntidad(entidad, incidentesValidos));
        }
        return criterio.generarRanking(incidentesPorEntidad);
    }

    public boolean estaDentroDeLaSemana(LocalDate fecha){
        return fecha.isAfter(this.fechaComienzoSemana) && fecha.isBefore(this.fechaFinSemana);
    }

    public void cambiarFechas(){
        this.setFechaFinSemana(this.fechaFinSemana.plusDays(7));
        this.setFechaComienzoSemana(this.fechaComienzoSemana.plusDays(7));
    }

    public List<Incidente> obtenerIncidentesDeEntidad(Entidad entidad, List<Incidente> incidentes){
        return incidentes.stream().filter(i -> i.getPrestacionDeServicio().getEntidad() == entidad).toList();
    }

}
