package domain.calculos;

import domain.comunidades.Comunidad;
import domain.comunidades.Incidente;
import domain.comunidades.NombreGradoConfianza;
import domain.comunidades.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CalculadorGradoConfianza {
    private List<CalculoGradoConfianza> calculosGradoConfianza;

    public CalculadorGradoConfianza() {
        this.calculosGradoConfianza = new ArrayList<>();
    }

    public void agregarCalculosGradoConfianza(CalculoGradoConfianza ... calculos){
        Collections.addAll(this.calculosGradoConfianza, calculos);
    }

    public void actualizarPuntosDeConfianza(List<Usuario> usuarios, List<Comunidad> comunidades, List<Incidente> incidentes){
        usuarios.forEach(u -> this.actualizarPuntosDeConfianzaDe(u, incidentes));

        comunidades.forEach(this::actualizarGradoConfianza);
    }

    public void actualizarPuntosDeConfianzaDe(Usuario usuario, List<Incidente> incidentes){
        this.calculosGradoConfianza.forEach(c -> c.actualizarGradoConfianza(usuario, incidentes));
    }

    private void actualizarGradoConfianza(Comunidad comunidad) {
        double multiplicadorConReservas = 0.2;
        List<Usuario> usuariosComunidad = comunidad.getUsuarios();
        double sumaPuntosConfianza = usuariosComunidad.stream()
                .mapToDouble(Usuario::getPuntosDeConfianza)
                .sum();

        double promedioConfianzaComunidad = sumaPuntosConfianza/usuariosComunidad.size();
        double puntosDeConfianzaComunidad = promedioConfianzaComunidad -
                usuariosComunidad.stream()
                        .filter(u -> u.getGradoDeConfianza().getNombreGradoConfianza() == NombreGradoConfianza.CON_RESERVAS)
                        .toList().size() * multiplicadorConReservas;
        comunidad.actualizarPuntosDeConfianza(puntosDeConfianzaComunidad);
    }
}
