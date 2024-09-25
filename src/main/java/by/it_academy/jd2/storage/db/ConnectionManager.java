package by.it_academy.jd2.storage.db;

import by.it_academy.jd2.storage.api.IConnectionManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionManager implements IConnectionManager {

    private static final DataSource dataSource;

    static {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        try {
            comboPooledDataSource.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        comboPooledDataSource.setJdbcUrl(System.getenv("DB_URL"));
        comboPooledDataSource.setUser(System.getenv("DB_USER"));
        comboPooledDataSource.setPassword(System.getenv("DB_PASSWORD"));
        comboPooledDataSource.setMaxPoolSize(25);

        dataSource = comboPooledDataSource;

    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
