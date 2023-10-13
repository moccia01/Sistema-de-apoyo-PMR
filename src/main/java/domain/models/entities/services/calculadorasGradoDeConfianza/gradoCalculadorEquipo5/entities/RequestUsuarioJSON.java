package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RequestUsuarioJSON {
    public UsuarioApi5 usuario;
    public List<IncidenteApi5> incidentes;

    public void cargar(Usuario usuario, List<Incidente> incidentes) {
        this.usuario.setId(usuario.getId());
        this.usuario.setApellido(usuario.getApellido());
        this.usuario.setPuntosDeConfianza(usuario.getPuntosDeConfianza());
        this.usuario.setId(usuario.getId());
        this.incidentes = new ArrayList<>();
        incidentes.forEach(i -> this.cargarIncidente(i));

    }

    public void cargarIncidente(Incidente incidente){

        IncidenteApi5 incidenteApi5 = new IncidenteApi5();
        UsuarioApi5 usuarioAnalizador = new UsuarioApi5();
        ServicioApi5 servicioAfectado = new ServicioApi5();
        UsuarioApi5 usuarioReportador = new UsuarioApi5();

        usuarioAnalizador.setId(incidente.getUsuarioCierre().getId());
        usuarioAnalizador.setNombre(incidente.getUsuarioCierre().getNombre());
        usuarioAnalizador.setApellido(incidente.getUsuarioCierre().getApellido());
        usuarioAnalizador.setPuntosDeConfianza(incidente.getUsuarioCierre().getPuntosDeConfianza());

        servicioAfectado.setId(incidente.getPrestacionDeServicio().getServicio().getId());

        usuarioReportador.setId(incidente.getUsuarioApertura().getId());
        usuarioReportador.setNombre(incidente.getUsuarioApertura().getNombre());
        usuarioReportador.setApellido(incidente.getUsuarioApertura().getApellido());
        usuarioReportador.setPuntosDeConfianza(incidente.getUsuarioApertura().getPuntosDeConfianza());

        incidenteApi5.setId(incidente.getId());
        incidenteApi5.setFechaCierre(incidente.getFechaHoraCierre());
        incidenteApi5.setFechaApertura(incidente.getFechaHoraApertura());
        incidenteApi5.setUsuarioAnalizador(usuarioAnalizador);
        incidenteApi5.setServicioAfectado(servicioAfectado);
        incidenteApi5.setUsuarioReportador(usuarioReportador);

        this.incidentes.add(incidenteApi5);
    }
}
