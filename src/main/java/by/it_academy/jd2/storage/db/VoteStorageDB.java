package by.it_academy.jd2.storage.db;


import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.entity.Comment;
import by.it_academy.jd2.entity.VoteEntity;
import by.it_academy.jd2.storage.api.IConnectionManager;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class VoteStorageDB implements IVoteStorage {


    private static final String INSERT_VOTE_QUERY = """
            INSERT INTO app.vote(create_at, user_name, artist_id, about)
            	VALUES (?,?,?,?) RETURNING id;
            """;
    private static final String INSERT_GENRE_QUERY = """
            INSERT INTO app.cross_vote_genre(vote_id, genre_id)
                VALUES(?,?);""";


    private static final String SELECT_ARTISTS_RESULT = """
            SELECT name, count(artist_id) AS votes
            FROM app.artist
            LEFT JOIN app.vote ON app.vote.artist_id = app.artist.id
            GROUP BY name ORDER BY votes DESC;""";
    private static final String SELECT_GENRES_RESULT = """ 
            SELECT name, count(genre_id) AS votes
            FROM app.genre
            LEFT JOIN app.cross_vote_genre ON app.genre.id = app.cross_vote_genre.genre_id
            GROUP BY name ORDER BY votes DESC;""";
    private static final String SELECT_COMMENTS = """
            SELECT  create_at, user_name, about
            	FROM app.vote;""";
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

    @Override
    public ResultsDTO getVotingResult() {
        return ResultsDTO.builder()
                .setArtists(getResults(SELECT_ARTISTS_RESULT))
                .setGenres(getResults(SELECT_GENRES_RESULT))
                .setComments(getComments())
                .build();

    }

    public Map<String, Long> getResults(String selectQuery) {
        Map<String, Long> results = new LinkedHashMap<>();
        try (Connection connect = connectionManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(selectQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    results.put(resultSet.getString("name"),
                            resultSet.getLong("votes"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();

        try (Connection connect = connectionManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_COMMENTS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comments.add(Comment.builder()
                            .setAuthor(resultSet.getString("user_name"))
                            .setText(resultSet.getString("about"))
                            .setDateTime(resultSet.getTimestamp("create_at").toLocalDateTime())
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }


}
