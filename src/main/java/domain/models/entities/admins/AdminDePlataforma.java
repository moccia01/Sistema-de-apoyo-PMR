package domain.models.entities.admins;

import domain.models.entities.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@Setter
@Getter
public class AdminDePlataforma extends EntidadPersistente {
    @Column
    private String nombre;

    @Column
    private String usuario;

    @Column
    private String contrasenia;
}
