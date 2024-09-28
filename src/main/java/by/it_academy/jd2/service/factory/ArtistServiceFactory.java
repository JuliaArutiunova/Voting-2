package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.service.ArtistService;
import by.it_academy.jd2.service.api.IArtistService;
import by.it_academy.jd2.storage.db.factory.ArtistStorageFactory;
import by.it_academy.jd2.storage.db.factory.VotingStorageFactory;

public class ArtistServiceFactory {

    private static final IArtistService instance = new ArtistService(ArtistStorageFactory.getInstance(),
            VoteServiceFactory.getInstance());

    private ArtistServiceFactory() {
    }

    public static IArtistService getInstance() {
        return instance;
    }
}
