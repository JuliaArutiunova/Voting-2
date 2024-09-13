package by.it_academy.jd2.storage.db;

import by.it_academy.jd2.storage.api.IGenresStorage;
import by.it_academy.jd2.storage.db.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
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

    private static final String SELECT_ALL_QUERY = """
            SELECT id, name FROM app.genre""";

    private static final String DELETE_GENRE_FROM_VOTE = "DELETE FROM app.cross_vote_genre WHERE genre_id = ?;";

    private static final String DELETE_GENRE = "DELETE FROM app.genre WHERE id = ? RETURNING name;";

    public GenresStorageDB() {
    }

    @Override
    public Long create(String genre) {
        try (Connection connect = DBUtils.getConnection();
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
    public Long[] create(ArrayList<String> genresList) {
        Long[] ids = new Long[genresList.size()];

        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(INSERT_QUERY)) {


            for (int i = 0; i < genresList.size(); i++) {
                statement.setString(1, genresList.get(i));

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
    public String get(Long id) {

        try (Connection connect = DBUtils.getConnection();
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
    public String delete(Long id) {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement1 = connect.prepareStatement(DELETE_GENRE_FROM_VOTE);
             PreparedStatement statement2 = connect.prepareStatement(DELETE_GENRE)) {

            statement1.setLong(1, id);
            statement1.executeUpdate();
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
