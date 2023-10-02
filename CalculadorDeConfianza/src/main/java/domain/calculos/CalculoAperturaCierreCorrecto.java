package domain.calculos;

import domain.comunidades.Incidente;
import domain.comunidades.Usuario;

import java.util.List;

public class CalculoAperturaCierreCorrecto extends CalculoGradoConfianza{

    public CalculoAperturaCierreCorrecto(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    @Override
    public List<Incidente> obtenerIncidentesQueCumplen(Usuario usuario, List<Incidente> incidentes) {
        List<Incidente> aperturasFraudulentas = new CalculoAperturaFraudulenta(-0.2).obtenerIncidentesQueCumplen(usuario, incidentes);
        List<Incidente> cierresFraudulentos = new CalculoCierreFraudulento(-0.2).obtenerIncidentesQueCumplen(usuario, incidentes);

        return incidentes.stream().filter( i -> !aperturasFraudulentas.contains(i) && !cierresFraudulentos.contains(i)
                        && (i.getUsuarioCierre() == usuario || i.getUsuarioApertura() == usuario))
                .toList();
    }
}
