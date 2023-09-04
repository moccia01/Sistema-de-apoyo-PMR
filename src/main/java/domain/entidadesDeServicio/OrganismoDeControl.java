package domain.entidadesDeServicio;

import domain.rankings.CriterioRanking;
import domain.rankings.GeneradorDeRankings;

import java.util.List;

public class OrganismoDeControl {
    private String nombre;
    private List<Entidad> entidades;

    public OrganismoDeControl(String nombre) {
        nombre = nombre;
    }

    public boolean estaAsociadoA(Entidad entidad) {
        return this.entidades.contains(entidad);
    }

    public List<Entidad> obtenerRankingSegun(CriterioRanking criterioRanking){
        return new GeneradorDeRankings().generarSegunCriterio(criterioRanking);
    }

}
