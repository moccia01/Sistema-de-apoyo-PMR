package domain.entidadesDeServicio;

import domain.entidadesDeServicio.Entidad;
import domain.rankings.CriterioRanking;
import domain.rankings.GeneradorDeRankings;
import lombok.Getter;

import java.util.List;

@Getter
public class EntidadPrestadora {
    private final String nombreEntidad;
    private List<Entidad> entidades;
    private GeneradorDeRankings generadorDeRankings;

    public EntidadPrestadora(String nombre) {
        nombreEntidad = nombre;
    }
}

