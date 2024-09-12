package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.*;
import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.storage.api.IArtistsStorage;
import by.it_academy.jd2.storage.api.IGenresStorage;


public class FormManagerService implements IFormManagerService {

    IArtistsStorage artistsStorageDB;
    IGenresStorage genresStorageDB;

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


    public ParticipantsDTO getParticipants() {
        return ParticipantsDTO.builder()
                .setArtists(artistsStorageDB.get())
                .setGenres(genresStorageDB.get())
                .build();
    }


}
