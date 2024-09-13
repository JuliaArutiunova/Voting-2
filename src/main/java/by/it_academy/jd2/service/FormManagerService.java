package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.*;
import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.storage.api.IArtistsStorage;
import by.it_academy.jd2.storage.api.IGenresStorage;


public class FormManagerService implements IFormManagerService {

    private IArtistsStorage artistsStorageDB;
    private IGenresStorage genresStorageDB;


    public FormManagerService(IArtistsStorage artistsStorageDB, IGenresStorage genresStorageDB) {
        this.artistsStorageDB = artistsStorageDB;
        this.genresStorageDB = genresStorageDB;
    }

    @Override
    public void create(FormCreateDTO dto) {

        if (dto.getNewArtists().size() < 2 || dto.getNewGenres().size() < 2) {
            throw new IllegalArgumentException("Выбрано менее двух участников");
        }
        artistsStorageDB.create(dto.getNewArtists());
        genresStorageDB.create(dto.getNewGenres());
    }


    @Override
    public void createArtist(String name) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Имя  артиста не введено");
        }
        if (artistsStorageDB.get().containsValue(name)) {
            throw new IllegalArgumentException("Артист с таким именем уже существует");
        }

        artistsStorageDB.create(name);

    }

    @Override
    public void createGenre(String name) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Имя жанра не введено");
        }
        if (genresStorageDB.get().containsValue(name)) {
            throw new IllegalArgumentException("Жанр с таким названием уже существует");
        }

        genresStorageDB.create(name);
    }

    @Override
    public String deleteArtist(String id) {
        if (id.isBlank()) {
            throw new IllegalArgumentException("Артист не был выбран");
        }
        return artistsStorageDB.delete(Long.parseLong(id));
    }

    public ParticipantsDTO getParticipants() {
        return ParticipantsDTO.builder()
                .setArtists(artistsStorageDB.get())
                .setGenres(genresStorageDB.get())
                .build();
    }

}
