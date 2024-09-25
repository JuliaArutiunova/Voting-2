package by.it_academy.jd2.storage.api;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionManager {
    Connection getConnection() throws SQLException;
}
