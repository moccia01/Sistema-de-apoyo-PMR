package domain.comunidades;

import domain.db.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "comunidad")
public class Comunidad extends EntidadPersistente {
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
