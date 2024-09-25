package by.it_academy.jd2.validation;

import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.service.api.IArtistService;
import by.it_academy.jd2.service.api.IGenreService;
import by.it_academy.jd2.validation.api.IVotingFormValidation;

import java.util.ArrayList;
import java.util.List;

public class VotingFormValidator implements IVotingFormValidation {
    private int minGenresNumber = 3;
    private int maxGenresNumber = 5;

    private IArtistService artistService;
    private IGenreService genreService;

    private List<Throwable> errors;

    public VotingFormValidator(IArtistService artistService, IGenreService genreService) {
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @Override
    public void validate(InfoFromClientDTO info) {
        errors = new ArrayList<>();

        if (info.getName().isBlank()) {
            errors.add(new IllegalArgumentException("Имя не введено"));
        }

        if (artistService.get(Long.valueOf(info.getArtist())) == null) {
            errors.add(new IllegalArgumentException("Такого артиста не существует"));
        }


        if (info.getGenres() == null) {
            errors.add(new IllegalArgumentException("Не выбраны жанры"));

        } else if (info.getGenres().length > maxGenresNumber || info.getGenres().length < minGenresNumber) {
            errors.add(new IllegalArgumentException("Выбрано неверное количество жанров"));

        } else {
            for (int i = 0; i < info.getGenres().length; i++) {
                if (genreService.get(Long.valueOf(info.getGenres()[i])) == null) {
                    errors.add(new IllegalArgumentException("Жанра с id " + info.getGenres()[i] +
                            " не существует"));
                }
            }
        }

        if (info.getComment().isBlank()) {
            errors.add(new IllegalArgumentException("Вы не написали комментарий"));
        }

    }

    public List<Throwable> getErrors() {
        return errors;
    }
}
