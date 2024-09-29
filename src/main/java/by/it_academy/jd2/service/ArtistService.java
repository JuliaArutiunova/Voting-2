package by.it_academy.jd2.service;

import by.it_academy.jd2.exception.DeleteParticipantException;
import by.it_academy.jd2.service.api.IArtistService;
import by.it_academy.jd2.service.api.IVoteService;
import by.it_academy.jd2.storage.api.IArtistsStorage;


import java.util.Map;

public class ArtistService implements IArtistService {

    private final IArtistsStorage artistsStorage;
    private final IVoteService voteService;

    public ArtistService(IArtistsStorage artistsStorage, IVoteService voteService) {
        this.artistsStorage = artistsStorage;
        this.voteService = voteService;
    }

    @Override
    public void create(String name) {
        if (artistsStorage.getByName(name) != null) {
            throw new IllegalArgumentException("Артист с таким именем уже существует");
        }
        artistsStorage.create(name);
    }

    @Override
    public String get(Long id) {
        return artistsStorage.getById(id);
    }


    @Override
    public Map<Long, String> get() {
        return artistsStorage.get();
    }

    @Override
    public void delete(Long id) {
        String artistName = get(id);
        if (artistName == null) {
            throw new IllegalArgumentException("Такого артиста не существует");
        } else if (voteService.getArtistResult(id) != 0) {
            throw new DeleteParticipantException();
        }
        artistsStorage.delete(id);
    }


}
