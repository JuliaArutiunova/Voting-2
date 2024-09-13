package by.it_academy.jd2.storage.db;

import by.it_academy.jd2.storage.api.IArtistsStorage;
import by.it_academy.jd2.storage.db.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
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

    private static final String SELECT_ALL_QUERY = """
            SELECT id, name FROM app.artist""";

    private static final String DELETE_ARTIST_FROM_VOTE = """
            UPDATE app.vote SET artist_id = null WHERE artist_id = ?;
            """;
    private static final String DELETE_ARTIST = """
            DELETE FROM app.artist WHERE id = ? RETURNING name;
            """;

    public ArtistsStorageDB() {
    }

    @Override
    public Long create(String artist) {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, artist);

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
    public Long[] create(ArrayList<String> artistsList) {
        Long[] ids = new Long[artistsList.size()];

        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(INSERT_QUERY)) {

            for (int i = 0; i < artistsList.size(); i++) {
                statement.setString(1, artistsList.get(i));

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {

                        ids[i] = resultSet.getLong(1);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ids;
    }

    @Override
    public Map<Long, String> get() {
        try (Connection connect = DBUtils.getConnection();
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
    public String get(Long id) {

        try (Connection connect = DBUtils.getConnection();
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

    @Override
    public String delete(Long id) {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement1 = connect.prepareStatement(DELETE_ARTIST_FROM_VOTE);
             PreparedStatement statement2 = connect.prepareStatement(DELETE_ARTIST)) {

            statement1.setLong(1, id);
            statement2.setLong(1,id);

            try (ResultSet resultSet = statement2.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
