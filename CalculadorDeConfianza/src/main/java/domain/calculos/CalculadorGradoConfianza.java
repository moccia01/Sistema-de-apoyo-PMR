package domain.calculos;

import domain.comunidades.Comunidad;
import domain.comunidades.Incidente;
import domain.comunidades.NombreGradoConfianza;
import domain.comunidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CalculadorGradoConfianza {

    public CalculadorGradoConfianza() {
    }

    public void actualizarPuntosDeConfianza(List<Usuario> usuarios, List<Comunidad> comunidades, List<Incidente> incidentes){
        usuarios.forEach(u -> this.actualizarPuntosDeConfianzaDe(u, incidentes));

        comunidades.forEach(this::actualizarGradoConfianza);

    }

    public void actualizarPuntosDeConfianzaDe(Usuario usuario, List<Incidente> incidentes){
        double multiplicadorAperturasFraudulentas = 0.2;
        double multiplicadorCierresFraudulentos = 0.2;
        double multiplicadorAperturasCierresCorrectos = 0.5;

        List<Incidente> aperturasFraudulentas = this.obtenerAperturasFraudulentas(usuario, incidentes);

        List<Incidente> cierresFraudulentos = this.obtenerCierresFraudulentos(usuario, incidentes);

        List<Incidente> aperturasCierresCorrectos = this.obtenerAperturasCierresCorrectos(incidentes, aperturasFraudulentas, cierresFraudulentos, usuario);

        double puntosNuevos = usuario.getPuntosDeConfianza() -
                aperturasFraudulentas.size() * multiplicadorAperturasFraudulentas -
                cierresFraudulentos.size() * multiplicadorCierresFraudulentos +
                aperturasCierresCorrectos.size() * multiplicadorAperturasCierresCorrectos;

        usuario.actualizarPuntosDeConfianza(puntosNuevos);
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

    private List<Incidente> obtenerAperturasFraudulentas(Usuario usuario, List<Incidente> incidentes) {
        return incidentes.stream().filter(i -> i.getUsuarioCierre() == usuario && i.minutosVigente() < 3).toList();
    }

    private List<Incidente> obtenerCierresFraudulentos(Usuario usuario, List<Incidente> incidentes) {
        List<Incidente> incidentesCerrados = incidentes.stream().filter(i -> i.estaCerrado() && i.getUsuarioCierre() == usuario).toList();
        List<Incidente> cierresFraudulentos = new ArrayList<>();

        for(Incidente incidenteCerrado:incidentesCerrados){
            for(Incidente incidente: incidentes.stream().filter(i -> i.tieneDiferenciaDe3MinutosCon(incidenteCerrado)).toList()){
                if(incidenteCerrado.esSimilarA(incidente) && !cierresFraudulentos.contains(incidente) && incidenteCerrado != incidente){
                    cierresFraudulentos.add(incidenteCerrado);
                }
            }
        }
        return cierresFraudulentos;
    }

    private List<Incidente> obtenerAperturasCierresCorrectos(List<Incidente> incidentes, List<Incidente> aperturasFraudulentas, List<Incidente> cierresFraudulentos, Usuario usuario) {
        return incidentes.stream().filter( i -> !aperturasFraudulentas.contains(i) && !cierresFraudulentos.contains(i)
                        && (i.getUsuarioCierre() == usuario || i.getUsuarioApertura() == usuario))
                .toList();
    }
}
