package by.it_academy.jd2.service;

import by.it_academy.jd2.exception.DeleteParticipantException;
import by.it_academy.jd2.service.api.IGenreService;
import by.it_academy.jd2.service.api.IVoteService;
import by.it_academy.jd2.storage.api.IGenresStorage;


import java.util.Map;

public class GenreService implements IGenreService {

    IGenresStorage genresStorage;
    private final IVoteService voteService;

    public GenreService(IGenresStorage genresStorage, IVoteService voteService) {
        this.genresStorage = genresStorage;
        this.voteService = voteService;
    }

    @Override
    public void create(String name) {
        if (genresStorage.getByName(name) != null) {
            throw new IllegalArgumentException("Жанр с таким именем уже существует");
        }
        genresStorage.create(name);
    }

    @Override
    public Map<Long, String> get() {
        return genresStorage.get();
    }

    public String get(Long id){
        return genresStorage.get(id);
    }

    @Override
    public void delete(Long id) {
        String genreName = get(id);
        if (genreName == null) {
            throw new IllegalArgumentException("Такого жанра не существует");
        } else if (voteService.getGenreResult() != 0) {
            throw new DeleteParticipantException();
        }
        genresStorage.delete(id);
    }


}
