package domain.entidadesDeServicio;

import domain.entidadesDeServicio.Entidad;
import domain.rankings.CriterioRanking;
import domain.rankings.GeneradorDeInforme;
import domain.rankings.GeneradorDeRankings;
import domain.rankings.InteresadoInforme;
import lombok.Getter;

import java.util.List;

@Getter
public class EntidadPrestadora implements InteresadoInforme {
    private final String nombreEntidad;
    private List<Entidad> entidades;
    private GeneradorDeInforme generadorDeInforme;

    public EntidadPrestadora(String nombre) {
        nombreEntidad = nombre;
    }

    public List<List<Entidad>> obtenerInforme(){
        return generadorDeInforme.generar(this);
    }

    public boolean estaAsociadoA(Entidad entidad){
        return this.entidades.contains(entidad);
    }

}

