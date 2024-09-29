package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.entity.VoteEntity;
import by.it_academy.jd2.exception.VotingFormException;
import by.it_academy.jd2.service.api.IVoteService;
import by.it_academy.jd2.storage.api.IVoteStorage;
import by.it_academy.jd2.validation.api.IVotingFormValidator;


import java.time.LocalDateTime;

public class VoteService implements IVoteService {


    private IVoteStorage voteStorage;
    private IVotingFormValidator validator;

    public VoteService(IVoteStorage voteStorage,IVotingFormValidator validator) {
        this.voteStorage = voteStorage;
        this.validator = validator;
    }


    @Override
    public void create(InfoFromClientDTO infoFromClientDTO) {
        validator.validate(infoFromClientDTO);
        if (!validator.getErrors().isEmpty()) {
            throw new VotingFormException(validator.getErrors());
        }

        voteStorage.create(VoteEntity.builder()
                .setCreateAt(LocalDateTime.now())
                .setAuthor(infoFromClientDTO.getName())
                .setArtist(Long.valueOf(infoFromClientDTO.getArtist()))
                .setGenres(idToLong(infoFromClientDTO.getGenres()))
                .setAbout(infoFromClientDTO.getComment())
                .build());
    }

    @Override
    public ResultsDTO getResults() {
        return voteStorage.getVotingResult();
    }

    public int getArtistResult(Long id) {
        return voteStorage.getArtistResult(id);
    }

    public int getGenreResult(Long id) {
        return voteStorage.getGenreResult(id);
    }


    private Long[] idToLong(String[] stringIds) {
        int arrayLength = stringIds.length;

        Long[] ids = new Long[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            ids[i] = Long.valueOf(stringIds[i]);
        }
        return ids;
    }

}
