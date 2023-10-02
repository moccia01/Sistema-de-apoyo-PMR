package domain.comunidad;

import domain.converters.RolAttributeConverter;
import domain.converters.RolTemporalAttributeConverter;
import domain.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "miembro")
public class Miembro extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column
    @Convert(converter = RolAttributeConverter.class)
    private Rol rol;

    @Column
    @Convert(converter = RolTemporalAttributeConverter.class)
    private RolTemporal rolTemporal;

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
    private Comunidad comunidad;


    public Miembro(Usuario usuario, Rol rol, Comunidad comunidad) {
        this.usuario = usuario;
        this.rol = rol;
        this.comunidad = comunidad;
    }
    public Miembro() {

    }
}
