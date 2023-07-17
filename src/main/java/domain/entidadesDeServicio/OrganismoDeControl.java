package domain.entidadesDeServicio;

import com.sun.jdi.connect.Connector;
import domain.entidadesDeServicio.Entidad;
import domain.rankings.GeneradorDeInforme;
import domain.rankings.GeneradorDeRankings;
import domain.rankings.InteresadoInforme;

import java.util.List;

public class OrganismoDeControl implements InteresadoInforme {
    private String nombre;
    private List<Entidad> entidades;
    private GeneradorDeInforme generadorDeInforme;

    public OrganismoDeControl(String nombre) {
        nombre = nombre;
    }

    public List<List<Entidad>> obtenerInforme(){
        return generadorDeInforme.generar(this);
    }

    public boolean estaAsociadoA(Entidad entidad) {
        return this.entidades.contains(entidad);
    }

}
