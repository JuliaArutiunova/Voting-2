package by.it_academy.jd2.storage.db;

import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.entity.Comment;
import by.it_academy.jd2.storage.api.IResultsStorage;
import by.it_academy.jd2.storage.db.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultStorageDB implements IResultsStorage {

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


    @Override
    public ResultsDTO get() {
        return ResultsDTO.builder()
                .setArtists(getResults(SELECT_ARTISTS_RESULT))
                .setGenres(getResults(SELECT_GENRES_RESULT))
                .setComments(getComments())
                .build();

    }

    public Map<String, Long> getResults(String selectQuery) {
        Map<String, Long> results = new LinkedHashMap<>();
        try (Connection connect = DBUtils.getConnection();
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

        try (Connection connect = DBUtils.getConnection();
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
