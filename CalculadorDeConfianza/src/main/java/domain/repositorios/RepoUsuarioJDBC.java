package domain.repositorios;

import domain.comunidades.Usuario;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.persistence.EntityManager;

public class RepoUsuarioJDBC implements RepoUsuarios {

    private BasicDataSource dataSource;

    public RepoUsuarioJDBC(BasicDataSource dataSource){
        super();
        this.dataSource = dataSource;
    }

    public List<Usuario> obtenerUsuarios(){

       // return EntityManager().;      //TODO Ver si esto evoluciona o utilizamos las querys del sistema
        return null;
    }
}
