package by.it_academy.jd2.service.factory;


import by.it_academy.jd2.service.GenreService;
import by.it_academy.jd2.service.api.IGenreService;
import by.it_academy.jd2.storage.db.factory.GenresStorageFactory;

public class GenreServiceFactory {
    private static final IGenreService instance = new GenreService(GenresStorageFactory.getInstance(),
            VoteServiceFactory.getInstance());

    private GenreServiceFactory() {
    }

    public static IGenreService getInstance() {
        return instance;
    }
}
