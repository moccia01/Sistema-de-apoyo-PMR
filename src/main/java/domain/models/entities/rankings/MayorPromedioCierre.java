package domain.models.entities.rankings;

import domain.models.entities.comunidad.Incidente;
import lombok.Getter;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class MayorPromedioCierre extends CriterioRanking{

    public long tiempoDeCierre(Incidente incidente){
        LocalDateTime fechaApertura = incidente.getFechaHoraApertura();
        LocalDateTime fechaCierre = incidente.getFechaHoraCierre();
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
