package domain.entidadesDeServicio;

import domain.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Servicio")
public class Servicio extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado", columnDefinition = "BOOL")
    private Boolean estado;
}
