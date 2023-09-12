package domain.repositorios;

import domain.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioUsuarios implements WithSimplePersistenceUnit {
    private List<Usuario> usuarios;

    public RepositorioUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuarios(Usuario ... usuarios){
        Collections.addAll(this.usuarios, usuarios);
    }

    public List<Usuario> obtenerUsuarios(){
        if(this.usuarios == null){
            usuarios = entityManager()
                    .createQuery("from Usuario")
                    .getResultList();
        }
        return this.usuarios;
    }
}
