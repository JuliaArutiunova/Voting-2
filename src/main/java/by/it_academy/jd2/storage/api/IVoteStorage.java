package by.it_academy.jd2.storage.api;


import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.entity.VoteEntity;

public interface IVoteStorage {

    Long create(VoteEntity voteEntity);

    ResultsDTO getVotingResult();

}
