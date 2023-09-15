package domain.calculos;

import domain.comunidades.Incidente;
import domain.comunidades.Usuario;

import java.util.List;

public abstract class CalculoGradoConfianza {
    protected double multiplicador;

    public void actualizarGradoConfianza(Usuario usuario, List<Incidente> incidentes) {
        List<Incidente> incidentesQueCumplen = this.obtenerIncidentesQueCumplen(usuario, incidentes);
        double puntosNuevos = usuario.getPuntosDeConfianza() + incidentesQueCumplen.size() * this.multiplicador;
        usuario.actualizarPuntosDeConfianza(puntosNuevos);
    }

    public abstract List<Incidente> obtenerIncidentesQueCumplen(Usuario usuario, List<Incidente> incidentes);
}
