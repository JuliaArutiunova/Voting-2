package by.it_academy.jd2.storage.db;

import by.it_academy.jd2.storage.api.IConnectionManager;
import by.it_academy.jd2.storage.api.IGenresStorage;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GenresStorageDB implements IGenresStorage {

    private static final String INSERT_QUERY = """
            INSERT INTO app.genre(name)
            	VALUES (?) RETURNING id;
            """;

    private static final String SELECT_BY_ID_QUERY = """
            SELECT name
            	FROM app.genre WHERE id = ?;""";

    private static final String SELECT_BY_NAME_QUERY = """
            SELECT id
            	FROM app.genre WHERE name = ?;""";

    private static final String SELECT_ALL_QUERY = """
            SELECT id, name FROM app.genre""";


    private static final String DELETE_GENRE_QUERY = """
            DELETE FROM app.genre WHERE id = ?;""";


    private final IConnectionManager connectionManager;

    public GenresStorageDB(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Long create(String genre) {
        try (Connection connect = connectionManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, genre);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }



    @Override
    public String get(Long id) {

        try (Connection connect = connectionManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID_QUERY);) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Long getByName(String name) {
        try (Connection connect = connectionManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_NAME_QUERY)) {

            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Map<Long, String> get() {
        try (Connection connect = connectionManager.getConnection();
             Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            Map<Long, String> result = new HashMap<>();
            while (resultSet.next()) {
                result.put(resultSet.getLong("id"), resultSet.getString("name"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GENRE_QUERY)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении");
        }
    }


}
