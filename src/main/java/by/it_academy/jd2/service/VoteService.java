package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.entity.VoteEntity;
import by.it_academy.jd2.exception.VotingFormException;
import by.it_academy.jd2.service.api.IVoteService;
import by.it_academy.jd2.storage.api.IVoteStorage;
import by.it_academy.jd2.validation.VotingFormValidator;


import java.time.LocalDateTime;

public class VoteService implements IVoteService {


    private final IVoteStorage voteStorage;
    private final VotingFormValidator validator;

    public VoteService(IVoteStorage voteStorage, VotingFormValidator validator) {
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
                .build());
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
