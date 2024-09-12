package by.it_academy.jd2.storage.api;


import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.dto.VoteDTO;

public interface IVotingStorage {

    Long create(VoteDTO voteDTO);

   // ResultsDTO get();

}
