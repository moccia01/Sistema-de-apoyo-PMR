package domain.rankings;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public abstract class CriterioRanking {
    public LocalDate fechaComienzoSemana; //se tiene q cambiar cada lunes
    public LocalDate fechaFinSemana; //se tiene q cambiar cada domingo

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
        return this.estaDentroDeLaSemana(incidente.getFechaApertura());
    }

    public boolean estaDentroDeLaSemana(LocalDate fecha){
        return fecha.isAfter(this.fechaComienzoSemana) && fecha.isBefore(this.fechaFinSemana);
    }

    public void cambiarFechas(){
        this.setFechaFinSemana(this.fechaFinSemana.plusDays(7));
        this.setFechaComienzoSemana(this.fechaComienzoSemana.plusDays(7));
    }
}
