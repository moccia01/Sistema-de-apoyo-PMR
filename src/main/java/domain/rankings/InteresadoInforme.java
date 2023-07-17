package domain.rankings;

import domain.entidadesDeServicio.Entidad;
import java.util.List;

public interface InteresadoInforme {

    List<List<Entidad>> obtenerInforme();

    boolean estaAsociadoA(Entidad entidad);

}
