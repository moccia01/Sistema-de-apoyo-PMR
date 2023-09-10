package domain.rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class CierreIncidentes extends CriterioRanking{

    public long tiempoDeCierre(Incidente incidente){
        LocalDateTime fechaApertura = incidente.getFechaApertura().atStartOfDay();
        LocalDateTime fechaCierre = incidente.getFechaCierre().atStartOfDay();
        Duration duration = Duration.between(fechaApertura, fechaCierre);
        return duration.getSeconds();

    }

    public double promedioTiempoCierre(List<Incidente> incidentes){
        return incidentes.stream().mapToDouble(this::tiempoDeCierre).sum() / incidentes.size();
    }

    @Override
    public boolean incidenteValido(Incidente incidente){
        return super.incidenteValido(incidente) && incidente.getEstado(); // si el estado es true significa que esta resuelto (cerrado)
    }

    public double transformarListaAValor(List<Incidente> incidentes){
        return this.promedioTiempoCierre(incidentes);
    }
}
