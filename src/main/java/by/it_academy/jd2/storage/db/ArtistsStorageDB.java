package by.it_academy.jd2.storage.db;

import by.it_academy.jd2.storage.api.IArtistsStorage;
import by.it_academy.jd2.storage.api.IConnectionManager;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ArtistsStorageDB implements IArtistsStorage {

    private static final String INSERT_QUERY = """
            INSERT INTO app.artist(name)
            	VALUES (?) RETURNING id;
            """;
    private static final String SELECT_BY_ID_QUERY = """
            SELECT name
            	FROM app.artist WHERE id = ?;""";
    private static final String SELECT_BY_NAME_QUERY = """
            SELECT id
            	FROM app.artist WHERE name = ?;""";
    private static final String SELECT_ALL_QUERY = """
            SELECT id, name FROM app.artist""";

    private static final String DELETE_ARTIST_QUERY = """
            DELETE FROM app.artist WHERE id = ?;
            """;

    private final IConnectionManager connectionManager;

    public ArtistsStorageDB(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Long create(String artist) {
        try (Connection connect = connectionManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, artist);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании артиста");
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

    public String getById(Long id) {

        try (Connection connect = connectionManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID_QUERY)) {

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
    public void delete(Long id) {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTIST_QUERY)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении");
        }


    }


}
