package domain.testPersistencia;

import domain.repositorios.DataSourceFactory;
import domain.repositorios.RepoUsuarioJDBC;
import org.hsqldb.jdbc.JDBCDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

public class prueba {

    private static final String DB = "usuarios";

    @Test
    public void nose(){
        RepoUsuarioJDBC repo = new RepoUsuarioJDBC(DataSourceFactory.createDataSource(DB));
    }


}
