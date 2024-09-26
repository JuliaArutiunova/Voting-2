package by.it_academy.jd2.storage.db;


import by.it_academy.jd2.entity.VoteEntity;
import by.it_academy.jd2.storage.api.IConnectionManager;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.sql.*;


public class VoteStorageDB implements IVoteStorage {


    private static final String INSERT_VOTE_QUERY = """
            INSERT INTO app.vote(create_at, user_name, artist_id, about)
            	VALUES (?,?,?,?) RETURNING id;
            """;
    private static final String INSERT_GENRE_QUERY = """
            INSERT INTO app.cross_vote_genre(vote_id, genre_id)
                VALUES(?,?);""";

    private final IConnectionManager connectionManager;

    public VoteStorageDB(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Long create(VoteEntity voteEntity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);
            statement.execute("BEGIN;");

            try (PreparedStatement voteStatement = connection.prepareStatement(INSERT_VOTE_QUERY);
                 PreparedStatement genreStatement = connection.prepareStatement(INSERT_GENRE_QUERY)) {

                voteStatement.setObject(1, voteEntity.getCreateAt());
                voteStatement.setString(2, voteEntity.getAuthor());
                voteStatement.setLong(3, voteEntity.getArtist());
                voteStatement.setString(4, voteEntity.getAbout());

                Long voteId = null;
                try (ResultSet resultSet = voteStatement.executeQuery()) {
                    while (resultSet.next()) {
                        voteId = resultSet.getLong("id");
                    }
                }
                if (voteId == null) {
                    throw new IllegalStateException("Ошибка получения id добавленного голоса");
                }

                for (int i = 0; i < voteEntity.getGenres().length; i++) {
                    genreStatement.setLong(1, voteId);
                    genreStatement.setLong(2, voteEntity.getGenres()[i]);
                    genreStatement.executeUpdate();
                    genreStatement.clearParameters();
                }
                statement.execute("COMMIT;");
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка добавления голоса");
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

}
