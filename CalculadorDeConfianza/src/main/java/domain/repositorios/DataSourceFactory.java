package domain.repositorios;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceFactory {

    public static BasicDataSource createDataSource(String db){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://localhost/" + db);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
        dataSource.setInitialSize(5);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }
}
