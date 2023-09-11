package domain.controllers;

import domain.comunidades.Comunidad;
import domain.comunidades.Incidente;
import domain.comunidades.Usuario;
import domain.repositorios.RepoComunidades;
import domain.repositorios.RepoIncidentes;
import domain.repositorios.RepoUsuarios;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ActualizacionGradoConfianzaController implements Handler{
    private RepoUsuarios repoUsuarios;
    private RepoComunidades repoComunidades;
    private RepoIncidentes repoIncidentes;

    public ActualizacionGradoConfianzaController(RepoUsuarios repoUsuarios, RepoComunidades repoComunidades) {
        super();
        this.repoUsuarios = repoUsuarios;
        this.repoComunidades = repoComunidades;

    }


    @Override
    public void handle(@NotNull Context context) throws Exception {
        this.actualizarPuntosDeConfianzaDeUsuarios(repoUsuarios.obtenerUsuarios(), repoComunidades, repoIncidentes);
    }

    public void actualizarPuntosDeConfianzaDeUsuarios(List<Usuario> usuarios, RepoComunidades repoComunidades, RepoIncidentes repoIncidentes){
        usuarios.forEach(u -> this.actualizarPuntosDeConfianzaDe(u,
                repoComunidades.obtenerComunidadesDe(u),
                repoIncidentes.obtenerIncidentesDe(u).stream().filter(Incidente::estaDentroDeLaSemana).toList()));

        // TODO updatear la base de datos con los usuarios y comunidades actualizadas
    }

    public void actualizarPuntosDeConfianzaDe(Usuario usuario, List<Comunidad> comunidades, List<Incidente> incidentes){
        double multiplicadorAperturasFraudulentas = 0.2;
        double multiplicadorCierresFraudulentos = 0.2;
        double multiplicadorAperturasCierresCorrectos = 0.5;

        List<Incidente> aperturasFraudulentas = this.obtenerAperturasFraudulentas(usuario, incidentes);

        List<Incidente> cierresFraudulentos = this.obtenerCierresFraudulentos(usuario, incidentes);

        List<Incidente> aperturasCierresCorrectos = this.obtenerAperturasCierresCorrectos(incidentes, aperturasFraudulentas, cierresFraudulentos);

        double puntosNuevos = usuario.getPuntosDeConfianza() -
                aperturasFraudulentas.size() * multiplicadorAperturasFraudulentas -
                cierresFraudulentos.size() * multiplicadorCierresFraudulentos +
                aperturasCierresCorrectos.size() * multiplicadorAperturasCierresCorrectos;


        usuario.actualizarPuntosDeConfianza(puntosNuevos);

        //TODO calcular grado de confianza de comunidades
   
    }

    private double calcularGradoDeConfianzaComunidad(Comunidad comunidad) {
        List<Usuario> usuariosComunidad = comunidad.getUsuarios();
        double sumaPuntosConfianza = 0.0;

        for (Usuario usuarioComunidad : usuariosComunidad) {
            sumaPuntosConfianza += usuarioComunidad.getPuntosDeConfianza();
        }

        double gradoDeConfianzaComunidad = sumaPuntosConfianza ;
        return gradoDeConfianzaComunidad;
    }

    private List<Incidente> obtenerAperturasFraudulentas(Usuario usuario, List<Incidente> incidentes) {
        return incidentes.stream().filter(i -> i.getUsuarioCierre() == usuario && i.minutosVigente() < 3).toList();
    }

    private List<Incidente> obtenerCierresFraudulentos(Usuario usuario, List<Incidente> incidentes) {
        List<Incidente> incidentesCerrados = incidentes.stream().filter(i -> i.estaCerrado() && i.getUsuarioCierre() == usuario).toList();
        List<Incidente> cierresFraudulentos = new ArrayList<>();

        for(Incidente incidenteCerrado:incidentesCerrados){
            for(Incidente incidente: incidentes.stream().filter(i -> i.tieneDiferenciaDe3MinutosCon(incidenteCerrado)).toList()){
                if(incidenteCerrado.esSimilarA(incidente) && !cierresFraudulentos.contains(incidente)){
                    cierresFraudulentos.add(incidenteCerrado);
                }
            }
        }

        return cierresFraudulentos;
    }

    private List<Incidente> obtenerAperturasCierresCorrectos(List<Incidente> incidentes, List<Incidente> aperturasFraudulentas, List<Incidente> cierresFraudulentos) {
        return incidentes.stream().filter( i -> !aperturasFraudulentas.contains(i) && !cierresFraudulentos.contains(i)).toList();
    }
}
