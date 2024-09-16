package by.it_academy.jd2.storage.db;


import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.storage.api.IVotingStorage;
import by.it_academy.jd2.storage.db.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VotingStorageDB implements IVotingStorage {


    private static final String INSERT_QUERY = """
            INSERT INTO app.vote(create_at, user_name, artist_id, about)
            	VALUES (?,?,?,?) RETURNING id;
            """;
    private static final String INSERT_GENRE_QUERY = """
            INSERT INTO app.cross_vote_genre(vote_id, genre_id)
                VALUES(?,?);""";


    public VotingStorageDB() {
    }

    @Override
    public Long create(VoteDTO voteDTO) {
        long voteId;
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(INSERT_GENRE_QUERY)) {

            voteId = createVote(voteDTO, connect);

            for (int i = 0; i < voteDTO.getGenres().length; i++) {
                statement.setLong(1, voteId);
                statement.setLong(2, voteDTO.getGenres()[i]);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return voteId;
    }


    public Long createVote(VoteDTO voteDTO, Connection connect) {

        try (PreparedStatement statement = connect.prepareStatement(INSERT_QUERY)) {

            statement.setObject(1, voteDTO.getCreateAt());
            statement.setString(2, voteDTO.getAuthor());
            statement.setLong(3, voteDTO.getArtist());
            statement.setString(4, voteDTO.getText());

            try (ResultSet resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
