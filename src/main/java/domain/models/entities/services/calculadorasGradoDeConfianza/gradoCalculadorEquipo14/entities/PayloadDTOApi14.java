package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.entidadesDeServicio.Servicio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class PayloadDTOApi14 {
    public List<UsuarioApi14> usuarios;
    public List<ComunidadApi14> comunidades;
    public List<IncidenteApi14> incidentes;

    public void cargar(Usuario usuario, Comunidad comunidad, List<Incidente> incidentes) {
        UsuarioApi14 usuarioApi14 = new UsuarioApi14();
        ComunidadApi14 comunidadApi14 = new ComunidadApi14();

        usuarioApi14 = this.cargarUsuario(usuario);

        comunidadApi14.setId(comunidad.getId());
        comunidadApi14.setUsuarios(this.crearUsuarios(comunidad.obtenerUsuarioAPartirDeMiembros()));

        incidentes.forEach(i ->{
            IncidenteApi14 incidenteNuevo = this.cargarIncidente(i);
            this.incidentes.add(incidenteNuevo);
        });
        this.usuarios.add(usuarioApi14);
        this.comunidades.add(comunidadApi14);
    }

    public List<UsuarioApi14> crearUsuarios(List<Usuario> usuarios){
        List<UsuarioApi14> usuariosNuevos = new ArrayList<>();

        usuarios.forEach(u ->{
            UsuarioApi14 usuarioNuevo = this.cargarUsuario(u);
            usuariosNuevos.add(usuarioNuevo);
        });

        return  usuariosNuevos;
    }

    public UsuarioApi14 cargarUsuario(Usuario usuario){
        UsuarioApi14 usuarioApi14 = new UsuarioApi14();
        usuarioApi14.setId(usuario.getId());
        usuarioApi14.setPuntosDeConfianza(usuario.getPuntosDeConfianza());
        usuarioApi14.setGradoDeConfianza(this.cargarGrado(usuario));

        return usuarioApi14;
    }

    public GradoDeConfianzaApi14 cargarGrado(Usuario usuario){
        GradoDeConfianzaApi14 gradoDeConfianza = new GradoDeConfianzaApi14();

        gradoDeConfianza.setPuntosMaximos(usuario.getGradoDeConfianza().puntosMaximos);
        gradoDeConfianza.setPuntosMinimos(usuario.getGradoDeConfianza().puntosMinimos);

        return gradoDeConfianza;
    }

    public IncidenteApi14 cargarIncidente(Incidente incidente){
        IncidenteApi14 incidenteApi14 = new IncidenteApi14();

        incidenteApi14.setId(incidente.getId());
        incidenteApi14.setEstado(incidente.getEstado());
        incidenteApi14.setDescripcion(incidente.getDescripcion());
        incidenteApi14.setFechaApertura(incidente.getFechaHoraApertura().toString());
        incidenteApi14.setFechaCierre(incidente.getFechaHoraCierre().toString());
        incidenteApi14.setUsuarioApertura(this.cargarUsuario(incidente.getUsuarioApertura()));
        incidenteApi14.setUsuarioCierre(this.cargarUsuario(incidente.getUsuarioCierre()));
        incidenteApi14.setPrestacionDeServicio(this.cargarPrestacion(incidente.getPrestacionDeServicio()));

        return incidenteApi14;
    }

    private PrestacionDeServicioApi14 cargarPrestacion(PrestacionDeServicio prestacionDeServicio) {
        PrestacionDeServicioApi14 prestacionDeServicioApi14 = new PrestacionDeServicioApi14();
        prestacionDeServicioApi14.setServicio(this.cargarServicio(prestacionDeServicio.getServicio()));
        prestacionDeServicioApi14.setEstablecimiento(this.cargarEstablecimiento(prestacionDeServicio.getEstablecimiento()));
        prestacionDeServicioApi14.setEntidad(this.cargarEntidad(prestacionDeServicio.getEntidad()));

        return prestacionDeServicioApi14;
    }

    private EntidadApi14 cargarEntidad(Entidad entidad) {
        EntidadApi14 entidadApi14 = new EntidadApi14();
        entidadApi14.setId(entidad.getId());
        entidadApi14.setNombre(entidad.getNombre());

        return entidadApi14;
    }

    private EstablecimientoApi14 cargarEstablecimiento(Establecimiento establecimiento) {
        EstablecimientoApi14 establecimientoApi14 = new EstablecimientoApi14();
        establecimientoApi14.setId(establecimiento.getId());
        establecimientoApi14.setNombre(establecimiento.getNombre());

        return establecimientoApi14;
    }

    public ServicioApi14 cargarServicio(Servicio servicio){
        ServicioApi14 servicioApi14 = new ServicioApi14();

        servicioApi14.setId(servicio.getId());
        servicioApi14.setEstado(servicio.getEstado());
        servicioApi14.setNombre(servicio.getNombre());

        return servicioApi14;
    }

}
