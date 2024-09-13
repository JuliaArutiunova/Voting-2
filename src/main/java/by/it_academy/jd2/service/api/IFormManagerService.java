package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.FormCreateDTO;
import by.it_academy.jd2.dto.ParticipantsDTO;

public interface IFormManagerService {
    void create(FormCreateDTO dto);

    void createArtist(String name);

    void createGenre(String name);

    ParticipantsDTO getParticipants();
}
