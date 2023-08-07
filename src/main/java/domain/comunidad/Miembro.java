package domain.comunidad;

import domain.mensajes.Configuraciones.MedioConfigurado;
import domain.mensajes.Configuraciones.TiempoConfigurado;
import domain.entidadesDeServicio.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Miembro {
    private Usuario usuario;
    private Rol rol;
    private List<Comunidad> comunidades;
    private RolTemporal rolTemporal;
    private TiempoConfigurado tiempoConfigurado;
    private MedioConfigurado medioConfigurado;

    public Miembro(Usuario usuario, Rol rol) {
        this.usuario = usuario;
        this.rol = rol;
        this.comunidades = new ArrayList<>();
    }

    public void agregarComunidad(Comunidad comunidad){
        comunidades.add(comunidad);
    }

    public void generarIncidente(PrestacionDeServicio prestacionDeServicio, String descripcion){
        comunidades.forEach(c -> c.generarIncidente(prestacionDeServicio, descripcion));
    }

    public void cerrarIncidente(Comunidad comunidad, Incidente incidente){
        comunidad.cerrarIncidente(incidente);
    }

    public boolean estaInteresadoEn(Incidente incidente){
        return this.usuario.getInteres().contieneEntidad(incidente.getPrestacionDeServicio().getEntidad()) &&
                this.usuario.getInteres().contieneServicio(incidente.getPrestacionDeServicio().getServicio());
    }

    public void cambiarRolTemporal(RolTemporal nuevoRol) {
        this.rolTemporal = nuevoRol;
    }

    public void mandarPendientes(){
        this.tiempoConfigurado.mandarPendientes(this);
    }

    public boolean estaCercaDe(Incidente incidente){
        return this.usuario.getLocalizacion().estaCercaDe(incidente.getLocalizacion());
    }

}
