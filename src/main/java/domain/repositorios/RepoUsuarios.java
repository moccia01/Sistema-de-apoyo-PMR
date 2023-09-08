package domain.repositorios;

import domain.comunidad.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RepoUsuarios {
    private List<Usuario> usuarios;

    public RepoUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuarios(Usuario ... usuarios){
        Collections.addAll(this.usuarios, usuarios);
    }

    public List<Usuario> obtenerUsuarios(){

        // TODO hacer la query a la bd con EntityManager

        return this.usuarios;
    }
}
