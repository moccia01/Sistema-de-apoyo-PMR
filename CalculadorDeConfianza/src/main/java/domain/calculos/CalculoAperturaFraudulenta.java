package domain.calculos;

import domain.comunidades.Incidente;
import domain.comunidades.Usuario;

import java.util.List;

public class CalculoAperturaFraudulenta extends CalculoGradoConfianza{

    public CalculoAperturaFraudulenta(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    @Override
    public List<Incidente> obtenerIncidentesQueCumplen(Usuario usuario, List<Incidente> incidentes) {
        return incidentes.stream().filter(i -> i.getUsuarioCierre() == usuario && i.minutosVigente() < 3).toList();
    }
}
