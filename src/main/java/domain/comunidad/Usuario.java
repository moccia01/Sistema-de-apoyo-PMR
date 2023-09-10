package domain.comunidad;

import domain.converters.MedioConfiguradoAttributeConverter;
import domain.db.EntidadPersistente;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.localizacion.Localizacion;
import domain.mensajes.Configuraciones.MedioConfigurado;
import domain.mensajes.Configuraciones.TiempoConfigurado;
import domain.validaciones.CredencialDeAcceso;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private CredencialDeAcceso credencialDeAcceso;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telefono")
    private String telefono;

    @OneToOne(cascade = CascadeType.ALL)
    private Interes interes;

    @Transient
    private Localizacion localizacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Miembro> miembros;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tiempo_configurado_id")
    private TiempoConfigurado tiempoConfigurado;

    @Column
    @Convert(converter = MedioConfiguradoAttributeConverter.class)
    private MedioConfigurado medioConfigurado;

    public Usuario() {
        this.miembros = new ArrayList<>();
    }

    public void setLocalizacion(String provincia, String departamento, String direccion){
        Localizacion localizacionSet = new Localizacion();
        localizacionSet.setProvincia(provincia);
        localizacionSet.setDireccion(departamento, direccion);
        this.localizacion = localizacionSet;
    }

    public void agregarMiembros(Miembro ... miembros){
        Collections.addAll(this.miembros, miembros);
    }

    public void generarIncidente(PrestacionDeServicio prestacionDeServicio, String descripcion){
        miembros.forEach(m -> m.getComunidad().generarIncidente(prestacionDeServicio, descripcion));
    }

    public void cerrarIncidente(Comunidad comunidad, Incidente incidente){
        comunidad.cerrarIncidente(incidente);
    }

    public boolean estaInteresadoEn(Incidente incidente){
        return this.interes.contieneEntidad(incidente.getPrestacionDeServicio().getEntidad()) &&
                this.interes.contieneServicio(incidente.getPrestacionDeServicio().getServicio());
    }

    public void mandarPendientes(){
        this.tiempoConfigurado.mandarPendientes(this);
    }

    public boolean estaCercaDe(Incidente incidente){
        return this.localizacion.estaCercaDe(incidente.getLocalizacion());
    }

    public List<Comunidad> obtenerComunidades(){
        return miembros.stream().map(Miembro::getComunidad).toList();
    }
}
