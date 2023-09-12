package domain.repositorios;

import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMiembros implements WithSimplePersistenceUnit {
    private List<Miembro> miembros;

    public RepositorioMiembros() {
        this.miembros = new ArrayList<>();
    }

    public List<Miembro> obtenerMiembros(){
        if(this.miembros == null){
            miembros = entityManager()
                    .createQuery("from Miembro")
                    .getResultList();
        }
        return this.miembros;
    }
}
