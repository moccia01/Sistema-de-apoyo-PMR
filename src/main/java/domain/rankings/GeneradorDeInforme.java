package domain.rankings;

import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.EntidadPrestadora;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class GeneradorDeInforme {
    GeneradorDeRankings generadorDeRankings;
    List<CriterioRanking> criterios;

    public GeneradorDeInforme() {
        this.criterios = new ArrayList<>();
    }

    public void agregarCriterios(CriterioRanking ... criterios){
        Collections.addAll(this.criterios, criterios);
    }

    public List<List<Entidad>> generar(InteresadoInforme interesadoInforme){
        List<List<Entidad>> informe = new ArrayList<>();
        criterios.forEach(c -> informe.add(generadorDeRankings.generarSegunCriterio(c).stream().filter(interesadoInforme::estaAsociadoA).toList()));
        return informe;
    }
}
