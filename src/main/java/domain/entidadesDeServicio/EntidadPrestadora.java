package domain.entidadesDeServicio;

import domain.comunidad.Incidente;
import domain.rankings.CriterioRanking;
import domain.rankings.GeneradorDeRankings;
import domain.rankings.RepositorioComunidades;
import lombok.Getter;

import java.util.List;

@Getter
public class EntidadPrestadora {
    private final String nombreEntidad;
    private List<Entidad> entidades;

    public EntidadPrestadora(String nombre) {
        nombreEntidad = nombre;
    }

    public boolean estaAsociadoA(Entidad entidad){
        return this.entidades.contains(entidad);
    }

    public List<Entidad> obtenerRankingSegun(CriterioRanking criterioRanking){
        return new GeneradorDeRankings().generarSegunCriterio(criterioRanking);
    }

}

